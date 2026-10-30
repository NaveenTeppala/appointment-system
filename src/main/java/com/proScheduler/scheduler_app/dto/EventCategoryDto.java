package com.proScheduler.scheduler_app.dto;

import com.proScheduler.scheduler_app.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventCategoryDto {
    private Long categoryId;
    private String categoryName;
    private String categoryTheme;
    private List<EventDto> events;
}
