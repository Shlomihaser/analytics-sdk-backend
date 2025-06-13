package com.shlomi.analytics.analytics_sdk_backend.service;

import com.shlomi.analytics.analytics_sdk_backend.model.UserEvent;
import com.shlomi.analytics.analytics_sdk_backend.repository.UserEventRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    private final UserEventRepository repository;

    public StatisticsService(UserEventRepository repository) {
        this.repository = repository;
    }

    public long getTotalEventsCount() {
        return repository.count();
    }

    public Map<String, Long> getEventsCountByType() {
        List<UserEvent> events = repository.findAll();
        return events.stream()
                .collect(Collectors.groupingBy(UserEvent::getEventType, Collectors.counting()));
    }

    public Map<String, Long> getEventsCountByMonthLastYear() {
        List<UserEvent> events = repository.findAll();

        Instant oneYearAgo = Instant.now().minusSeconds(365 * 24 * 3600);

        List<UserEvent> filteredEvents = events.stream()
                .filter(event -> event.getEventTimestamp().isAfter(oneYearAgo))
                .collect(Collectors.toList());

        Map<YearMonth, Long> countsByMonth = filteredEvents.stream()
                .collect(Collectors.groupingBy(event -> {
                    LocalDate date = LocalDate.ofInstant(event.getEventTimestamp(), ZoneId.systemDefault());
                    return YearMonth.from(date);
                }, Collectors.counting()));

        return countsByMonth.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        e -> e.getKey().toString(),
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        java.util.LinkedHashMap::new
                ));
    }

    // חישוב ממוצע אירועים למשתמש
    public double getAverageEventsPerUser() {
        List<UserEvent> events = repository.findAll();

        if (events.isEmpty()) return 0;

        Map<String, Long> countsByUser = events.stream()
                .collect(Collectors.groupingBy(UserEvent::getUserId, Collectors.counting()));

        return (double) events.size() / countsByUser.size();
    }

    // חישוב אירועים יומיים לפי משתמש
    public Map<String, Map<LocalDate, Long>> getDailyEventsPerUser() {
        List<UserEvent> events = repository.findAll();

        return events.stream()
                .collect(Collectors.groupingBy(UserEvent::getUserId,
                        Collectors.groupingBy(event -> LocalDate.ofInstant(event.getEventTimestamp(), ZoneId.systemDefault()),
                                Collectors.counting())));
    }
}