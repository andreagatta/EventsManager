package org.elis.eventsmanager.controller;

import org.elis.eventsmanager.dto.request.CreatePlaceRequest;
import org.elis.eventsmanager.dto.request.SigninRequest;
import org.elis.eventsmanager.dto.response.PlaceResponse;
import org.elis.eventsmanager.dto.response.SeatResponse;
import org.elis.eventsmanager.dto.response.SectionResponse;
import org.elis.eventsmanager.model.Place;
import org.elis.eventsmanager.model.Seat;
import org.elis.eventsmanager.model.Section;
import org.elis.eventsmanager.model.User;
import org.elis.eventsmanager.service.definition.PlaceService;
import org.elis.eventsmanager.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    PlaceService service;

    @PostMapping("/admin/create")
    public ResponseEntity<Void> createPlace(@RequestBody CreatePlaceRequest request){
        service.createPlace(request);
            return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/find_all")
    public ResponseEntity<List<PlaceResponse>> findAll() {
        List<Place> places = new ArrayList<>((service.findAll()));
        if (places.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "cannot find any place, ask an Admin to add some");
        else {
            List<PlaceResponse> placeResponses = new ArrayList<>();
            for (Place place : places) {
                PlaceResponse placeResponse = new PlaceResponse();
                placeResponse.setName(place.getName());
                placeResponse.setWay(place.getWay());
                placeResponse.setProvince(place.getProvince());
                placeResponse.setCity(place.getCity());
                placeResponse.setCAP(place.getCAP());
                for (Section section : place.getSections()) {
                    SectionResponse sectionResponse = new SectionResponse();
                    sectionResponse.setName(section.getName());
                    for (Seat seat : section.getSeats()) {
                        SeatResponse seatResponse = new SeatResponse();
                        seatResponse.setName(seat.getName());
                        sectionResponse.getSeats().add(seatResponse);
                    }
                placeResponse.getSections().add(sectionResponse);
                }
                placeResponses.add(placeResponse);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(placeResponses);
        }
    }
}
