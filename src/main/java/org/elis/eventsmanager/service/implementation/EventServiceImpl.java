package org.elis.eventsmanager.service.implementation;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.elis.eventsmanager.dto.request.CreateCategoryRequest;
import org.elis.eventsmanager.dto.request.CreateEventInstanceRequest;
import org.elis.eventsmanager.dto.request.CreateEventRequest;
import org.elis.eventsmanager.dto.request.CreateTicketRequest;
import org.elis.eventsmanager.model.*;
import org.elis.eventsmanager.repository.*;
import org.elis.eventsmanager.service.definition.EventService;
import org.elis.eventsmanager.util.AdminCheck;
import org.elis.eventsmanager.util.VendorCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public void createEvent(CreateEventRequest request) {
        //validation

        if(request==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "you have to insert something");
        }

        Optional<User> optionalAdmin = userRepository.findByEmailAndPassword(request.getAdminEmail(), request.getAdminPassword());
        if(optionalAdmin.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cannot find admin");

        }else {
            User admin = optionalAdmin.get();
            if(!VendorCheck.checkAuthorization(admin)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "you are not allowed");


            }else{
                Event event = new Event();
                event.setUser(admin);
                event.setName(request.getName());
                event.setDescription(request.getDescription());

                for(CreateCategoryRequest createCategoryRequest : request.getCategories()){
                    Category category = new Category();
                    category.setName(createCategoryRequest.getName());

                    event.getCategories().add(category);
                    category.getEvents().add(event);
                }

                for(CreateEventInstanceRequest createEventInstanceRequest : request.getEventInstances()){
                    EventInstance eventInstance = new EventInstance();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                    String stringStartDate = createEventInstanceRequest.getStartDate();
                    LocalDateTime startDate = LocalDateTime.parse(stringStartDate, formatter);

                    String stringFinishDate = createEventInstanceRequest.getFinishDate();
                    LocalDateTime finishDate = LocalDateTime.parse(stringFinishDate, formatter);

                    if(startDate.isAfter(finishDate))
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "start date is not before finish date");

                    eventInstance.setStartDate(startDate);
                    eventInstance.setFinishDate(finishDate);
                    //place validation
                    Place place = placeRepository.findById(createEventInstanceRequest.getPlace()).orElse(null);
                    if(place==null)
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "you have to insert the place");

                    eventInstance.setPlace(place);

                    for(CreateTicketRequest createTicketRequest : createEventInstanceRequest.getTickets()){
                            Ticket ticket = new Ticket();
                            ticket.setPrice(createTicketRequest.getPrice());
                            ticket.setUser(null);

                        Seat seat = seatRepository.findById(createTicketRequest.getSeat()).orElse(null);
                        if(seat==null)
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "you have to insert the seat");

                        ticket.setSeat(seat);
                        seat.getTickets().add(ticket);

                        ticket.setEventInstance(eventInstance);
                        eventInstance.getTickets().add(ticket);
                    }

                    event.getEventInstances().add(eventInstance);
                    eventInstance.setEvent(event);
                }

                try {
                    event = eventRepository.save(event);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not insert event");
                }
            }
            }
        }

    }

