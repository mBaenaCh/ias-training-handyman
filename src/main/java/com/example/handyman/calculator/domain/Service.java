package com.example.handyman.calculator.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class Service {
    private final Id serviceId;
    private final ServiceType type;
    private final LocalDateTime initDateTime;

    public Service(Id serviceId, ServiceType type, LocalDateTime initDateTime) {
        Objects.requireNonNull(serviceId, "The ID must not be null");
        Objects.requireNonNull(type, "The service type must not be null");
        Objects.requireNonNull(initDateTime, "The service initial date time must not be null");

        this.serviceId = serviceId;
        this.type = type;
        this.initDateTime = initDateTime;
    }
}
