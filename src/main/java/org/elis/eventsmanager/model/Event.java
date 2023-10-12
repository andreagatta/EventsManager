package org.elis.eventsmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;

    @ManyToMany(mappedBy = "events", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<EventInstance> eventInstances = new ArrayList<>();

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(nullable = false, updatable = false, name = "creator_id")
    private User user;


}
