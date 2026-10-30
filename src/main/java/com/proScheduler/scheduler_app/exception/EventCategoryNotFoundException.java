package com.proScheduler.scheduler_app.exception;

public class EventCategoryNotFoundException extends RuntimeException{
    public EventCategoryNotFoundException(String message){
        super(message);
    }
}
