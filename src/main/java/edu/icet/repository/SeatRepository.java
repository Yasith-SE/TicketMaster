package edu.icet.repository;

import edu.icet.model.entity.SeatsEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<SeatsEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM SeatsEntity s WHERE s.id = :id")
    Optional<SeatsEntity> findByIdWithLock(@Param("id") Long id);
}