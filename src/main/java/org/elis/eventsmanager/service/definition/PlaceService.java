package org.elis.eventsmanager.service.definition;

import org.elis.eventsmanager.dto.request.CreatePlaceRequest;
import org.elis.eventsmanager.model.Place;

import java.util.List;

public interface PlaceService {

    public void createPlace(CreatePlaceRequest request);
    public List<Place> findAll();
}
