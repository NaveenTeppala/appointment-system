package com.proScheduler.scheduler_app.service;


import com.proScheduler.scheduler_app.dto.EventDto;
import com.proScheduler.scheduler_app.entity.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface EventService {
    public List<EventDto> getAllEvents();

    public Event createEvent(EventDto data);

    public Event editEvent(EventDto data);

    public List<Event> deleteEvent(Long id);

    public List<EventDto> getEventsByDay(LocalDate date);

    public List<EventDto> getEventsByWeek(LocalDate weekStart);

    public List<EventDto> getEventsByMonth(YearMonth monthStart);
}
