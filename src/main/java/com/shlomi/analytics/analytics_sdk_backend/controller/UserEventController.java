package com.shlomi.analytics.analytics_sdk_backend.controller;

import com.shlomi.analytics.analytics_sdk_backend.model.UserEvent;
import com.shlomi.analytics.analytics_sdk_backend.service.UserEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class UserEventController {

    private final UserEventService service;

    public UserEventController(UserEventService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserEvent> createEvent(@RequestBody UserEvent event) {
        UserEvent saved = service.createEvent(event);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<UserEvent>> getAllEvents() {
        return ResponseEntity.ok(service.getAllEvents());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        service.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
