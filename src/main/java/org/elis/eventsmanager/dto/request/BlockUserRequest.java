package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BlockUserRequest {
    @NotEmpty(message = "you have to insert an admin email")
    @Email(message = "admin email is not valid")
    private String adminEmail;
    @NotEmpty(message = "you have to insert an admin password")
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{6,15})", message = "admin password is not valid")
    private String adminPassword;
    @NotNull(message = "you have to insert a toBlockId")
    @Min(value = 2, message = "cannot block or unlock SUPERADMIN")
    private long toBlockId;
}
