package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BlockUserRequest {
    @NotNull(message = "you have to insert a toBlockId")
    private long toBlockId;
}
