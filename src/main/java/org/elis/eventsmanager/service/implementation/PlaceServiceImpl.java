package org.elis.eventsmanager.service.implementation;

import org.elis.eventsmanager.dto.request.CreatePlaceRequest;
import org.elis.eventsmanager.dto.request.CreateSeatRequest;
import org.elis.eventsmanager.dto.request.CreateSectionRequest;
import org.elis.eventsmanager.model.Place;
import org.elis.eventsmanager.model.Seat;
import org.elis.eventsmanager.model.Section;
import org.elis.eventsmanager.model.User;
import org.elis.eventsmanager.repository.PlaceRepository;
import org.elis.eventsmanager.repository.SeatRepository;
import org.elis.eventsmanager.repository.SectionRepository;
import org.elis.eventsmanager.repository.UserRepository;
import org.elis.eventsmanager.service.definition.PlaceService;
import org.elis.eventsmanager.service.definition.UserService;
import org.elis.eventsmanager.util.AdminCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Place> findAll(){
        return placeRepository.findAll();
    }
    @Override
    public List<Place> findAllByRemovedIsFalse(){
        return placeRepository.findAllByRemovedIsFalse();
    }

    @Override
    public void createPlace(CreatePlaceRequest request) {

            Place place = new Place();
            place.setName(request.getPlaceName());
            place.setWay(request.getPlaceWay());
            place.setCity(request.getPlaceCity());
            place.setProvince(request.getPlaceProvince());
            place.setCAP(request.getPlaceCAP());

            for (CreateSectionRequest csr : request.getSections()) {
                Section section = new Section();
                section.setName(csr.getSectionName());

                for (CreateSeatRequest crs : csr.getSeats()) {
                    Seat seat = new Seat();
                    seat.setName(crs.getSeatName());
                    seat.setSection(section);
                    section.getSeats().add(seat);
                }
                section.setPlace(place);
                place.getSections().add(section);
            }

            place.setRemoved(false);

            try {
                place = placeRepository.save(place);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "exception thrown after saving changes on createPlace");
            }

        }
}
