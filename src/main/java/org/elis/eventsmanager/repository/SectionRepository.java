package org.elis.eventsmanager.repository;

import org.elis.eventsmanager.model.Place;
import org.elis.eventsmanager.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section,Long> {

   public Optional<Section> findAllByPlace(Place place);

}
