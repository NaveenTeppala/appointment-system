package com.proScheduler.scheduler_app.repository;

import com.proScheduler.scheduler_app.entity.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventCategoryRepo extends JpaRepository<EventCategory,Long> {
    public List<EventCategory> findAll();

    public Optional<EventCategory> findByCategoryId(Long id);
}
