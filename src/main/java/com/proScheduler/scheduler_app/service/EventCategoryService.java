package com.proScheduler.scheduler_app.service;

import com.proScheduler.scheduler_app.dto.EventCategoryDto;
import com.proScheduler.scheduler_app.entity.EventCategory;

import java.util.List;

public interface EventCategoryService {
    public EventCategory createEventCategory(EventCategoryDto eventCategoryDto);

    public List<EventCategoryDto> getAllEventCategories();

    public EventCategory updateEventCategory(EventCategoryDto eventCategoryDto);
}
