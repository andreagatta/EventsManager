package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data

public class LoginRequest {
    @NotBlank(message = "the email cannot be null or empty")
    @Email(message = "the inserted email is not valid")
    private String email;
    @NotBlank(message = "the password cannot be null or empty")
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{6,15})", message = "not valid password")
    private String password;
}
