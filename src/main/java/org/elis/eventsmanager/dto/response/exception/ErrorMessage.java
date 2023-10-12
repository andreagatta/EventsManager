package org.elis.eventsmanager.dto.response.exception;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ErrorMessage {
    private LocalDateTime date;
    private Map<String, String> error;
}
