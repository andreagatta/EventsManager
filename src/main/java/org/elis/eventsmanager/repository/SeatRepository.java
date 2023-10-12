package org.elis.eventsmanager.repository;

import org.elis.eventsmanager.model.Place;
import org.elis.eventsmanager.model.Seat;
import org.elis.eventsmanager.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat,Long> {
    public Optional<Seat> findById(long id);

}
