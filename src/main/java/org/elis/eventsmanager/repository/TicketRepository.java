package org.elis.eventsmanager.repository;

import org.elis.eventsmanager.model.EventInstance;
import org.elis.eventsmanager.model.Ticket;
import org.elis.eventsmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    List<Ticket> findAllByEventInstanceAndUserIsNull(EventInstance e);
    List<Ticket> findAllByEventInstance_IdAndUserIsNotNull(long id);

}
