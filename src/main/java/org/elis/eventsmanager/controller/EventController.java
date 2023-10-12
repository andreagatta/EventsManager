package org.elis.eventsmanager.controller;


import org.elis.eventsmanager.dto.request.CreateEventRequest;
import org.elis.eventsmanager.service.definition.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService service;

    @PostMapping("/create")
    public ResponseEntity<Void> createEvent(@RequestBody CreateEventRequest request) {
        service.createEvent(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
