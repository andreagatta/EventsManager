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
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String way;
    private String city;
    private String province;
    private String CAP;

    @OneToMany(mappedBy = "place",  cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<EventInstance> eventInstances = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Section> sections = new ArrayList<>();

}
