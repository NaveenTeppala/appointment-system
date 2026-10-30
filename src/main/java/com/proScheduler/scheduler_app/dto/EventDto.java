package com.proScheduler.scheduler_app.dto;


import com.proScheduler.scheduler_app.entity.EventCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    private Long eventId;

    @NotBlank(message = "Title is mandatory")
    private String title;

    private String description;

    @NotNull(message = "Start date/time is mandatory")
    private LocalDateTime startDateTime;

    @NotNull(message = "End date/time is mandatory")
    private LocalDateTime endDateTime;

    private String location;

    private LocalDateTime createdOn;

    private EventCategory category;

}
