package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SigninRequest {
    @NotBlank(message = "the name cannot be null or empty")
    private String name;
    @NotBlank(message = "the surname cannot be null or empty")
    private String surname;
    @NotBlank(message = "the email cannot be null or empty")
    @Email(message = "the inserted email is not valid")
    private String email;
    @NotBlank(message = "the password cannot be null or empty")
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{6,15})", message = "not valid password")
    private String password;
    @NotBlank(message = "the repeated password cannot be null or empty")
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{6,15})", message = "not valid repeated password")
    private String repeatedPassword;
    @Past(message = "you cannot be born in the future")
    private LocalDate birthDate;
    @Size(min = 16, max = 16, message = "the size of codeFiscale is not valid")
    private String codeFiscale;
}
