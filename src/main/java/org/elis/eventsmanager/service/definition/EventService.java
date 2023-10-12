package org.elis.eventsmanager.service.definition;


import org.elis.eventsmanager.dto.request.CreateEventRequest;

public interface EventService {
    public void createEvent(CreateEventRequest request);
}
