package com.proScheduler.scheduler_app.service;

import com.proScheduler.scheduler_app.dto.ApiResponse;
import com.proScheduler.scheduler_app.dto.EventCategoryDto;
import com.proScheduler.scheduler_app.dto.EventDto;
import com.proScheduler.scheduler_app.entity.EventCategory;
import com.proScheduler.scheduler_app.exception.EventCategoryNotFoundException;
import com.proScheduler.scheduler_app.repository.EventCategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.server.Http2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventCategoryServiceImpl implements EventCategoryService{
    private final EventCategoryRepo eventCategoryRepo;
    @Override
    public EventCategory createEventCategory(EventCategoryDto eventCategoryDto) {
        EventCategory eventCategory=EventCategory.builder().
                categoryName(eventCategoryDto.getCategoryName()).
                categoryTheme(eventCategoryDto.getCategoryTheme()).
                build();
        return eventCategoryRepo.save(eventCategory);
    }

    @Override
    public List<EventCategoryDto> getAllEventCategories() {
        return eventCategoryRepo.findAll().stream().map(this::eventCategoryDtoMapper).toList();
    }

    @Override
    public EventCategory updateEventCategory(EventCategoryDto eventCategoryDto) {
        EventCategory updateObj = eventCategoryRepo.findByCategoryId(eventCategoryDto.getCategoryId()).orElseThrow(()-> new EventCategoryNotFoundException("Category not found"));
        if(eventCategoryDto.getCategoryName()!=null){
            updateObj.setCategoryName(eventCategoryDto.getCategoryName());
        }
        if(eventCategoryDto.getCategoryTheme()!=null) {
            updateObj.setCategoryTheme(eventCategoryDto.getCategoryTheme());
        }
        return updateObj;
    }

    private EventCategoryDto eventCategoryDtoMapper(EventCategory eventCategory){
        return EventCategoryDto.builder().
                categoryId(eventCategory.getCategoryId()).
                categoryName(eventCategory.getCategoryName()).
                categoryTheme(eventCategory.getCategoryTheme()).
                events(eventCategory.getEvents().stream()
                .map(event -> EventDto.builder()
                        .eventId(event.getEventId())
                        .title(event.getTitle())
                        .startDateTime(event.getStartDateTime())
                        .endDateTime(event.getEndDateTime())
                        .location(event.getLocation())
                        .description(event.getDescription())
                        .createdOn(event.getCreatedOn())
                        .build())
                .toList())
                .build();
    }
}
