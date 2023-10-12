package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CreateSectionRequest {
    @NotBlank(message = "you have to insert a section name")
    private String sectionName;
    //SEATS
    @NotEmpty(message = "you have to insert some seats")
    private List<CreateSeatRequest> seats;
}
