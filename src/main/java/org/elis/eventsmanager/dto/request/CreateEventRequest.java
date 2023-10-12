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
    private List<CreateCategoryRequest> categories;
    @NotEmpty(message = "you have to insert some event instances")
    private List<CreateEventInstanceRequest> eventInstances;
    @NotEmpty(message = "you have to insert an admin email")
    @Email(message = "admin email is not valid")
    private String adminEmail;
    @NotEmpty(message = "you have to insert an admin password")
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{6,15})", message = "admin password is not valid")
    private String adminPassword;


}
