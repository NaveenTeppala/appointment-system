package com.proScheduler.scheduler_app.repository;

import com.proScheduler.scheduler_app.dto.EventDto;
import com.proScheduler.scheduler_app.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepo extends JpaRepository<Event,Long> {
   List<Event> findAll();

   Optional<Event> findByEventId(Long id);

   List<Event> findByStartDateTimeBetween(LocalDateTime start,LocalDateTime end);

   @Query("SELECT e FROM Event e WHERE e.startDateTime BETWEEN :weekStart AND :weekEnd")
   List<Event> findEventsInWeek(@Param("weekStart") LocalDateTime weekStart,@Param("weekEnd") LocalDateTime weekEnd);

   @Query("SELECT e FROM Event e WHERE e.startDateTime BETWEEN :monthStart AND :monthEnd")
   List<Event> findEventsInMonth(@Param("monthStart") LocalDateTime monthStart , @Param("monthEnd") LocalDateTime monthEnd);

}
