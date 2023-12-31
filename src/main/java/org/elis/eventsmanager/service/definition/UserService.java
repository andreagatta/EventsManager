package org.elis.eventsmanager.service.definition;

import org.elis.eventsmanager.dto.request.BlockUserRequest;
import org.elis.eventsmanager.dto.request.ChangeRoleRequest;
import org.elis.eventsmanager.dto.request.LoginRequest;
import org.elis.eventsmanager.dto.request.SigninRequest;
import org.elis.eventsmanager.model.User;

import java.util.List;

public interface UserService {
    public User login(LoginRequest request);
    public void clientSignin(SigninRequest request);
    public void blockUser(BlockUserRequest request, User user);
    public void unlockUser(BlockUserRequest request, User user);
    public void changeRole(ChangeRoleRequest request, User user);
    public List<User> findAll();
    public User findByEmail(String email);

}
