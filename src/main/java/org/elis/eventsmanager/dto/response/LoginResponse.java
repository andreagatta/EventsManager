package org.elis.eventsmanager.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private long id;
    private String email;
    private String role;
    private int years;
}
