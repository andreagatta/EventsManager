package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.elis.eventsmanager.model.Category;
import org.elis.eventsmanager.model.EventInstance;
import org.elis.eventsmanager.model.User;

import java.util.List;
@Data
public class CreateEventRequest {
    @NotEmpty(message = "you have to insert a event name")
    private String name;
    @NotEmpty(message = "you have to insert a event description")
    private String description;
    @NotEmpty(message = "you have to insert some categories")
    //private List<CreateCategoryRequest> categories;
    private List<Long> categories;
    @NotEmpty(message = "you have to insert some event instances")
    private List<CreateEventInstanceRequest> eventInstances;
}
