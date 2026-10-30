package com.proScheduler.scheduler_app.controller;

import com.proScheduler.scheduler_app.dto.ApiResponse;
import com.proScheduler.scheduler_app.dto.EventCategoryDto;
import com.proScheduler.scheduler_app.entity.EventCategory;
import com.proScheduler.scheduler_app.service.EventCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/event-categories")
public class EventCategoryController {

    @Autowired
    private  EventCategoryService eventCategoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<EventCategory>> createEventCategory(@Valid @RequestBody EventCategoryDto eventCategoryDto){
       EventCategory saved= eventCategoryService.createEventCategory(eventCategoryDto);
       ApiResponse<EventCategory> response = new ApiResponse<>(
               HttpStatus.OK.value(),
               "Event Category created successfully",
               LocalDateTime.now(),
               saved
       );
       return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EventCategoryDto>> getAllEventCategories(){
        List<EventCategoryDto> eventCategory = eventCategoryService.getAllEventCategories();
        ApiResponse<List<EventCategoryDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Event Category fetched successfully",
                LocalDateTime.now(),
                eventCategory
        );
        return ResponseEntity.ok(eventCategory);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<EventCategory>> updateEventCategory(@RequestBody EventCategoryDto eventCategoryDto){
        EventCategory eventCategory = eventCategoryService.updateEventCategory(eventCategoryDto);
        ApiResponse<EventCategory> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Event Category updated successfully",
                LocalDateTime.now(),
                eventCategory
        );
        return  ResponseEntity.ok(response);
    }
}
