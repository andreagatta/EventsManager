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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable= false, updatable = false, unique = true)
    private String name;

    @ManyToMany( cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "category_event",
               joinColumns =  @JoinColumn(name = "category_id", nullable = false),
               inverseJoinColumns = @JoinColumn(name = "event_id", nullable = false))
    private List<Event> events = new ArrayList<>();

}
