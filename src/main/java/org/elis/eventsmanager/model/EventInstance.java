package org.elis.eventsmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EventInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime  startDate;
    private LocalDateTime finishDate;

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="event_id", nullable = false, updatable = false)
    private Event event;

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(nullable = false)
    private Place place;

    @OneToMany(mappedBy = "eventInstance",  cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Ticket> tickets = new ArrayList<>();

}
