package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.elis.eventsmanager.model.Ticket;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class CreateEventInstanceRequest {
    @Future(message = "the date for an instance cannot be before now")
    private String startDate;
    @Future(message = "the date for an instance cannot be before now")
    private String finishDate;
    //place id
    @NotNull(message = "you have to insert a place")
    private long place;
    @NotEmpty(message = "you have to insert some tickets")
    private List<CreateTicketRequest> tickets;

}
