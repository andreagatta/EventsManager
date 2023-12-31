package org.elis.eventsmanager.controller;

import jakarta.validation.Valid;
import org.elis.eventsmanager.Exception.NotValidDataException;
import org.elis.eventsmanager.dto.request.BlockUserRequest;
import org.elis.eventsmanager.dto.request.ChangeRoleRequest;
import org.elis.eventsmanager.dto.request.LoginRequest;
import org.elis.eventsmanager.dto.response.LoginResponse;
import org.elis.eventsmanager.dto.request.SigninRequest;
import org.elis.eventsmanager.dto.response.UserResponse;
import org.elis.eventsmanager.security.TokenUtil;
import org.elis.eventsmanager.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.elis.eventsmanager.model.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.elis.eventsmanager.util.UtilPath.*;

@RestController
public class UserController {

    @Autowired
    UserService service;
@Autowired
    TokenUtil tokenUtil;
    //__________________________________G E N E R A L_______________________________________________________


    @PostMapping(LOGIN_USER_CLIENT)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        User user = service.login(request);
        String token = tokenUtil.generateToken(user);
        LoginResponse l = new LoginResponse();
        l.setId(user.getId());
        l.setRole(user.getRole().name());
        l.setEmail(user.getEmail());
        l.setYears((int) ChronoUnit.YEARS.between(user.getBirthDate(), LocalDate.now()));
        return ResponseEntity.status(HttpStatus.OK).header("Authorization", token).body(l);
    }


    @GetMapping(FIND_ALL_USER_ADMIN)
    public ResponseEntity<List<UserResponse>> findAll() {
        List<User> users = new ArrayList<>(service.findAll());
        if (users.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "cannot find any user");
        else {
            List<UserResponse> userResponses = new ArrayList<>();
            for (User user : users) {
                UserResponse userResponse = new UserResponse();
                userResponse.setName(user.getName());
                userResponse.setRole((user.getRole()));
                userResponse.setEmail((user.getEmail()));
                userResponse.setBlocked((user.isBlocked()));
                userResponse.setSurname((user.getSurname()));
                userResponse.setPassword(user.getPassword());
                userResponse.setBirthDate(user.getBirthDate());
                userResponse.setCodeFiscale(user.getCodeFiscale());
                userResponses.add(userResponse);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponses);
        }
    }


    //______________________________S U P E R A D M I N____________________________________________________

    @PutMapping(CHANGE_ROLE_USER_SUPERADMIN)
    public ResponseEntity<Void> changeRole(@Valid @RequestBody ChangeRoleRequest request, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        User user = (User) usernamePasswordAuthenticationToken.getPrincipal();
            service.changeRole(request, user);
            return ResponseEntity.status(HttpStatus.OK).build();
    }

    //___________________________________A D M I N________________________________________________________


    @PutMapping(BLOCK_USER_ADMIN)
    public ResponseEntity<Void> blockUser(@Valid @RequestBody BlockUserRequest request, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        User user =(User) usernamePasswordAuthenticationToken.getPrincipal();
            service.blockUser(request, user);
            return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(UNLOCK_USER_ADMIN)
    public ResponseEntity<Void> unlockUser(@Valid @RequestBody BlockUserRequest request, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        User user = (User) usernamePasswordAuthenticationToken.getPrincipal();
           service.unlockUser(request, user);
            return ResponseEntity.status(HttpStatus.OK).build();
    }

    //___________________________________V E N D O R_______________________________________________________


    //___________________________________C L I E N T_______________________________________________________

    @PostMapping(SIGNIN_USER_CLIENT)
    public ResponseEntity<Void> ClientSignin(@Valid @RequestBody SigninRequest request) {
        service.clientSignin(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
