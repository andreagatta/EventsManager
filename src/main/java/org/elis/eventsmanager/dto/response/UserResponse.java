package org.elis.eventsmanager.dto.response;

import jakarta.persistence.Column;
import lombok.Data;
import org.elis.eventsmanager.model.Role;

import java.time.LocalDate;

@Data
public class UserResponse {
    private String name;
    private String surname;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String codeFiscale;
    private Role role;
    private boolean blocked;
}
