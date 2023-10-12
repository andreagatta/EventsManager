package org.elis.eventsmanager.dto.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.elis.eventsmanager.model.Event;

import java.util.List;

@Data
public class CreateCategoryRequest {
    @NotEmpty(message = "you have to insert a category")
    private String name;
}
