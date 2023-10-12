package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.elis.eventsmanager.model.Role;

@Data
public class ChangeRoleRequest {
    @NotNull(message = "you have to insert a ToChangeRoleId")
    private long idToChangeRole;
    @NotNull(message = "you have to insert a role for toChangeRole")
    private Role role;
}
