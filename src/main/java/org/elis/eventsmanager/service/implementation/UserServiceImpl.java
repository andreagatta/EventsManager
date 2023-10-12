package org.elis.eventsmanager.service.implementation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.elis.eventsmanager.Exception.NotValidDataException;
import org.elis.eventsmanager.Exception.UserNotFoundException;
import org.elis.eventsmanager.dto.request.BlockUserRequest;
import org.elis.eventsmanager.dto.request.ChangeRoleRequest;
import org.elis.eventsmanager.dto.request.LoginRequest;
import org.elis.eventsmanager.dto.request.SigninRequest;
import org.elis.eventsmanager.model.Role;
import org.elis.eventsmanager.model.User;
import org.elis.eventsmanager.repository.UserRepository;
import org.elis.eventsmanager.service.definition.UserService;
import org.elis.eventsmanager.util.AdminCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

@Autowired
private UserRepository userRepository;

private final Validator validator;

    public UserServiceImpl(Validator validator) {
        this.validator = validator;
    }


    //findAll, findById, saveAll
    @Override
public List<User> findAll(){
    return userRepository.findAll();
}

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    //handle exception with genericHandler
    @Override
    public User login(LoginRequest request){

        //exception Handled by ExceptionHandler

        /*Map<String, String> error = new TreeMap<>();

        if(request==null){
            error.put("request","the request cannot be null");
            throw new NotValidDataException(error);
        }

        if(request.getEmail()==null){
            error.put("request","the email cannot be null");
            throw new NotValidDataException(error);
        }

        if(request.getPassword()==null){
            error.put("request","the password cannot be null");
            throw new NotValidDataException(error);
        }

        if(!error.isEmpty()){
            throw new NotValidDataException(error);
        }

         */

        Optional<User> user = userRepository.findByEmailAndPasswordAndBlockedIsNotNull(request.getEmail(),request.getPassword());
        return user.orElseThrow(UserNotFoundException::new);


    }

    @Override
    public void clientSignin(SigninRequest request){

    //create new User

        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setBirthDate(request.getBirthDate());
        user.setCodeFiscale(request.getCodeFiscale());
        user.setRole(Role.CLIENT);


        //save User in db
        try {
            user = userRepository.save(user);
        }catch(DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the user already exists");
        }
    }

    @Override
    public void blockUser(BlockUserRequest request, User user){

        //validation


        Optional<User> optionalToBlock = userRepository.findById(request.getToBlockId());
        if(optionalToBlock.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "to block not found");
        }

        User toBlock = optionalToBlock.get();

        if(toBlock.getRole().equals(Role.SUPERADMIN) && user.getRole().equals(Role.ADMIN)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "you cannot block a SUPERADMIN");
        }

            toBlock.setBlocked(true);

        try{
            toBlock = userRepository.save(toBlock);

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "exception thrown after saving changes on lock");
        }

    }

    @Override
    public void unlockUser(BlockUserRequest request, User user){

        //validation

        Optional<User> optionalToUnlock = userRepository.findById(request.getToBlockId());
        if(optionalToUnlock.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "to unlock not found");
        }

        User toUnlock = optionalToUnlock.get();

        if(toUnlock.getRole().equals(Role.SUPERADMIN) && user.getRole().equals(Role.ADMIN)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "you cannot unlock a SUPERADMIN");
        }

            toUnlock.setBlocked(false);

        try{
            toUnlock = userRepository.save(toUnlock);

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "exception thrown after saving changes on unlock");
        }
    }


    @Override
    public void changeRole(ChangeRoleRequest request, User user){

        //validation


        Optional<User> optionalToChangeRole = userRepository.findById(request.getIdToChangeRole());
        if(optionalToChangeRole.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "toChangeRole not found");
        }

        User toChangeRole = optionalToChangeRole.get();

        if(toChangeRole.getRole().equals(Role.SUPERADMIN) && user.getRole().equals(Role.ADMIN)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "you cannot change role to SUPERADMIN");
        }

            toChangeRole.setRole(request.getRole());

        try{
            toChangeRole = userRepository.save(toChangeRole);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "exception thrown after saving changes on change Role");
        }


    }

}
