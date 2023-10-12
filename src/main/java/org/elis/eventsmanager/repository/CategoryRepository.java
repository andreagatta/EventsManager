package org.elis.eventsmanager.repository;

import org.elis.eventsmanager.model.Category;
import org.elis.eventsmanager.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {


}
