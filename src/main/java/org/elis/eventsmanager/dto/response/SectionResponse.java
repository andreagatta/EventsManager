package org.elis.eventsmanager.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SectionResponse {
    private String name;
    private List<SeatResponse> seats = new ArrayList<>();
}