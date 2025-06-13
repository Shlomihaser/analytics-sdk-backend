package com.shlomi.analytics.analytics_sdk_backend.controller;

import com.shlomi.analytics.analytics_sdk_backend.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    // סך כל האירועים במסד
    @GetMapping("/total-events")
    public ResponseEntity<Long> getTotalEvents() {
        return ResponseEntity.ok(statisticsService.getTotalEventsCount());
    }

    // סך אירועים לפי סוג
    @GetMapping("/events-by-type")
    public ResponseEntity<Map<String, Long>> getEventsByType() {
        return ResponseEntity.ok(statisticsService.getEventsCountByType());
    }

    // סך אירועים לפי חודש בשנה האחרונה
    @GetMapping("/events-by-month")
    public ResponseEntity<Map<String, Long>> getEventsByMonth() {
        return ResponseEntity.ok(statisticsService.getEventsCountByMonthLastYear());
    }

    // ממוצע אירועים למשתמש
    @GetMapping("/average-events-per-user")
    public ResponseEntity<Double> getAverageEventsPerUser() {
        return ResponseEntity.ok(statisticsService.getAverageEventsPerUser());
    }

    // אירועים יומיים לפי משתמש
    @GetMapping("/daily-events-per-user")
    public ResponseEntity<Map<String, Map<LocalDate, Long>>> getDailyEventsPerUser() {
        return ResponseEntity.ok(statisticsService.getDailyEventsPerUser());
    }
}
