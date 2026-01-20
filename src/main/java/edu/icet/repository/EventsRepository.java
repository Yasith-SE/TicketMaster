package edu.icet.repository;

import edu.icet.model.entity.EventsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<EventsEntity, Long> {
}