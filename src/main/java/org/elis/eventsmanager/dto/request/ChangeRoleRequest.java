package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.elis.eventsmanager.model.Role;

@Data
public class ChangeRoleRequest {
    @NotEmpty(message = "you have to insert an admin email")
    @Email(message = "admin email is not valid")
    private String adminEmail;
    @NotEmpty(message = "you have to insert an admin password")
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{6,15})", message = "admin password is not valid")
    private String adminPassword;
    @NotNull(message = "you have to insert a ToChangeRoleId")
    private long idToChangeRole;
    @NotNull(message = "you have to insert a role for toChangeRole")
    private Role role;
}
