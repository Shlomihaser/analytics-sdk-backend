package com.shlomi.analytics.analytics_sdk_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "user_events")
public class UserEvent {

    @Id
    private String id;
    private String userId;
    private String eventType;
    private Instant eventTimestamp;
    private String metadata;

    public UserEvent() {}

    public UserEvent(String userId, String eventType, Instant eventTimestamp, String metadata) {
        this.userId = userId;
        this.eventType = eventType;
        this.eventTimestamp = eventTimestamp;
        this.metadata = metadata;
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public Instant getEventTimestamp() { return eventTimestamp; }
    public void setEventTimestamp(Instant eventTimestamp) { this.eventTimestamp = eventTimestamp; }

    public String getMetadata() { return metadata; }
    public void setMetadata(String metadata) { this.metadata = metadata; }
}