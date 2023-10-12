package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateSeatRequest {
   @NotBlank(message = "you have to insert a seat name")
   private String seatName;
}
