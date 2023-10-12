package org.elis.eventsmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private float price;

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="seat_id", nullable = false)
    private Seat seat;

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="event_instance_id", nullable = false)
    private EventInstance eventInstance;

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="user_id", nullable = true)
    private User user;

}
