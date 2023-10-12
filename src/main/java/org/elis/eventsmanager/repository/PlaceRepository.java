package org.elis.eventsmanager.repository;

import org.elis.eventsmanager.model.Place;
import org.elis.eventsmanager.model.User;
import org.elis.eventsmanager.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place,Long> {
    public Optional<Place> findById(long id);



}
