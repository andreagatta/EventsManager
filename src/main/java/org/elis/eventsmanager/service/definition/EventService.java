package org.elis.eventsmanager.service.definition;


import org.elis.eventsmanager.dto.request.CreateEventRequest;
import org.elis.eventsmanager.model.User;

public interface EventService {
    public void createEvent(CreateEventRequest request, User user);
}
