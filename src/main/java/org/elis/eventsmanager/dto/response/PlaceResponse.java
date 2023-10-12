package org.elis.eventsmanager.dto.response;

import lombok.Data;
import org.elis.eventsmanager.model.Section;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlaceResponse {
    private String name;
    private String way;
    private String city;
    private String province;
    private String CAP;
    private boolean removed;

    private List<SectionResponse> sections = new ArrayList<>();
}
