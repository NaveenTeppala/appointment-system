package com.proScheduler.scheduler_app.controller;

import com.proScheduler.scheduler_app.dto.ApiResponse;
import com.proScheduler.scheduler_app.dto.EventDto;
import com.proScheduler.scheduler_app.entity.Event;
import com.proScheduler.scheduler_app.service.EventService;
import com.proScheduler.scheduler_app.service.EventServiceImpl;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private final EventServiceImpl eventService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EventDto>>> getAllEvents(){
        List<EventDto> events = eventService.getAllEvents();
        ApiResponse<List<EventDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Events fetched Successfully",
                LocalDateTime.now(),
                events
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Event>> createEvent(@Valid @RequestBody EventDto eventDto){
        Event saved=eventService.createEvent(eventDto);
        ApiResponse<Event> response= new ApiResponse<>(
                HttpStatus.OK.value(),
                "Event Created Successfully",
                LocalDateTime.now(),
                saved
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Event>> updateEvent(@Valid @RequestBody EventDto eventDto){
        Event update = eventService.editEvent(eventDto);
        ApiResponse<Event> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Event updated successfully",
                LocalDateTime.now(),
                update
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<ApiResponse<List<Event>>> deleteEvent(@PathVariable Long eventId){
        List<Event> events = eventService.deleteEvent(eventId);
        ApiResponse<List<Event>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Event deleted successfully",
                LocalDateTime.now(),
                events
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/day/{date}")
    public ResponseEntity<ApiResponse<List<EventDto>>> getEventsByDay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        List<EventDto> events = eventService.getEventsByDay(date);
        ApiResponse<List<EventDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Event fetched successfully",
                LocalDateTime.now(),
                events
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/week/{weekStart}")
    public ResponseEntity<ApiResponse<List<EventDto>>> getEventsByWeek(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate weekStart){
        List<EventDto> events = eventService.getEventsByWeek(weekStart);
        ApiResponse<List<EventDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Event fetched successfully",
                LocalDateTime.now(),
                events
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/month/{yearMonth}")
    public ResponseEntity<ApiResponse<List<EventDto>>> getEventsByMonth(@PathVariable @DateTimeFormat(pattern = "yyyy-MM") YearMonth yearMonth) {
        List<EventDto> events = eventService.getEventsByMonth(yearMonth);
        ApiResponse<List<EventDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Event fetched successfully",
                LocalDateTime.now(),
                events
        );
        return ResponseEntity.ok(response);
    }

}
