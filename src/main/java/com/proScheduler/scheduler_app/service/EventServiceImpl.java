package com.proScheduler.scheduler_app.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.services.calendar.Calendar;
import com.proScheduler.scheduler_app.dto.EventDto;
import com.proScheduler.scheduler_app.entity.Event;
import com.proScheduler.scheduler_app.entity.EventCategory;
import com.proScheduler.scheduler_app.exception.EventCategoryNotFoundException;
import com.proScheduler.scheduler_app.exception.EventNotFoundException;
import com.proScheduler.scheduler_app.repository.EventCategoryRepo;
import com.proScheduler.scheduler_app.repository.EventRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements  EventService{

    private  final EventRepo eventRepo;
    private final EventCategoryRepo eventCategoryRepo;
    @Override
    public List<EventDto> getAllEvents() {
        List<EventDto> events = eventRepo.findAll().stream().map(this::eventDtoMapper).toList();
        if(events.isEmpty()){
            throw  new EventNotFoundException("No Events found");
        }
        return events;
    }

    @Override
    public Event createEvent(EventDto eventDto){
        EventCategory eventCategory = eventCategoryRepo.findByCategoryId(eventDto.getCategory().getCategoryId()).orElseThrow(()->new EventCategoryNotFoundException("Category not found"));
        Event event = Event.builder().
                createdOn(LocalDateTime.now()).
                startDateTime(eventDto.getStartDateTime()).
                endDateTime(eventDto.getEndDateTime()).
                title(eventDto.getTitle()).
                description(eventDto.getDescription()==null?null: eventDto.getDescription()).
                location(eventDto.getLocation()==null?null:eventDto.getLocation()).
                eventCategory(eventCategory).
                build();
        return eventRepo.save(event);
    }

    @Override
    public Event editEvent(EventDto eventDto) {
        Event eventObj=eventRepo.findByEventId(eventDto.getEventId()).orElseThrow(
                ()->new EventNotFoundException("Event not found with"+eventDto.getEventId())
        );
        if(eventObj.getTitle()!=null){
            eventObj.setTitle(eventDto.getTitle());
        }
        if(eventObj.getDescription()!=null){
            eventObj.setDescription(eventDto.getDescription());
        }
        if(eventObj.getStartDateTime()!=null){

            eventObj.setStartDateTime(eventDto.getStartDateTime());
        }
        if(eventObj.getEndDateTime()!=null){
            eventObj.setEndDateTime(eventDto.getEndDateTime());
        }
        eventObj.setCreatedOn(LocalDateTime.now());
        return eventRepo.save(eventObj);
    }

    @Override
    public List<Event> deleteEvent(Long id) {
        Event event = eventRepo.findByEventId(id).orElseThrow(()->new EventNotFoundException("Event not found with"+id));
        eventRepo.delete(event);
        return eventRepo.findAll();
    }

    @Override
    public List<EventDto> getEventsByDay(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        return eventRepo.findByStartDateTimeBetween(start,end).stream().map(this::eventDtoMapper).toList();
    }

    @Override
    public List<EventDto> getEventsByWeek(LocalDate weekStart) {
        LocalDateTime start = weekStart.atStartOfDay();
        LocalDateTime end = weekStart.plusDays(6).atTime(LocalTime.MAX);
        return eventRepo.findEventsInMonth(start,end).stream().map(this::eventDtoMapper).toList();
    }

    @Override
    public List<EventDto> getEventsByMonth(YearMonth monthStart) {
        LocalDateTime start = monthStart.atDay(1).atStartOfDay();
        LocalDateTime end = monthStart.atEndOfMonth().atTime(LocalTime.MAX);
        return eventRepo.findEventsInMonth(start,end).stream().map(this::eventDtoMapper).toList();
    }

    private EventDto eventDtoMapper(Event event){
        return EventDto.builder().
                eventId(event.getEventId()).
                title(event.getTitle()).
                location(event.getLocation()).
                startDateTime(event.getStartDateTime()).
                endDateTime(event.getEndDateTime()).
                description(event.getDescription()).
                createdOn(LocalDateTime.now()).
                category(event.getEventCategory()).
                build();
    }
}
