// package com.proScheduler.scheduler_app.service;

// import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
// import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
// import com.google.api.client.json.jackson2.JacksonFactory;
// import com.google.api.services.calendar.Calendar;
// import com.proScheduler.scheduler_app.entity.Event;
// import lombok.AllArgsConstructor;
// import lombok.NoArgsConstructor;
// import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
// import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
// import org.springframework.stereotype.Service;

// //import java.util.Calendar;

// import java.util.List;

// @Service
// @AllArgsConstructor
// public class GoogleCalendarService {
//     private  final OAuth2AuthorizedClientService clientService;

//     public List<Event> fetchEvents(String principalName) throws  Exception{
//         OAuth2AuthorizedClient client = clientService.loadAuthorizedClient("google",principalName);

//         GoogleCredential credential = new GoogleCredential().setAccessToken(client.getAccessToken().getTokenValue());

//         Calendar calendar = new Calendar.Builder(
//                 GoogleNetHttpTransport.newTrustedTransport(),
//                 JacksonFactory.getDefaultInstance(),
//                 credential
//         ).setApplicationName("Spring Boot Scheduler").build();
// //        Calendar.Events events = service.events().list("primary")
// //                .setMaxResults(10)
// //                .setOrderBy("startTime")
// //                .setSingleEvents(true)
// //                .execute();

// //        return events.getItems();
//     }
// }
