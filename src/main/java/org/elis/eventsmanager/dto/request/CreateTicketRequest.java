package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.elis.eventsmanager.model.EventInstance;
import org.elis.eventsmanager.model.Seat;
import org.elis.eventsmanager.model.User;

@Data
public class CreateTicketRequest {
    @NotNull(message = "price cannot be null")
    private float price;
    @NotNull(message = "you have to add a seat to the ticket")
    private long seat;

}
