package com.shlomi.analytics.analytics_sdk_backend.service;

import com.shlomi.analytics.analytics_sdk_backend.model.UserEvent;
import com.shlomi.analytics.analytics_sdk_backend.repository.UserEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEventService {

    private final UserEventRepository repository;

    public UserEventService(UserEventRepository repository) {
        this.repository = repository;
    }

    public UserEvent createEvent(UserEvent event) {
        return repository.save(event);
    }

    public List<UserEvent> getAllEvents() {
        return repository.findAll();
    }

    public void deleteEvent(String id) {
        repository.deleteById(id);
    }
}