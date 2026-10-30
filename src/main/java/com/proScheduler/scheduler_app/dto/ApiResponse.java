package com.proScheduler.scheduler_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
public class ApiResponse<T>{

    private int status;
    private String message;
    private LocalDateTime timeStamp;
    private T data;
}
