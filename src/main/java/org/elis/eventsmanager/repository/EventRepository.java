package org.elis.eventsmanager.repository;

import org.elis.eventsmanager.model.Event;
import org.elis.eventsmanager.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {


}
