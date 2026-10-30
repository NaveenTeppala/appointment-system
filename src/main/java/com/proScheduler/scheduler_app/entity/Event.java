package com.proScheduler.scheduler_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Table(name="event")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id")
    private Long eventId;

    @Column(name="title")
    private String title;

    @Column(name="start_date_time")
    private LocalDateTime startDateTime;

    @Column(name="end_date_time")
    private LocalDateTime endDateTime;

    private String location;

    private String description;

    @Column(name="created_on")
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private EventCategory eventCategory;
}
