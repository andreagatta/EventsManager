package org.elis.eventsmanager.controller;


import org.elis.eventsmanager.dto.request.CreateEventRequest;
import org.elis.eventsmanager.model.User;
import org.elis.eventsmanager.service.definition.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static org.elis.eventsmanager.util.UtilPath.CREATE_EVENT_VENDOR;

@RestController
public class EventController {

    @Autowired
    EventService service;

    @PostMapping(CREATE_EVENT_VENDOR)
    public ResponseEntity<Void> createEvent(@RequestBody CreateEventRequest request, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken /*, @RequestHeader("ruolo") String ruolo*/) {
        User user =(User) usernamePasswordAuthenticationToken.getPrincipal();
        service.createEvent(request, user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
