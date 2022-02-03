package com.example.handyman.calculator.domain;


import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Report {

    private final Id technicianId;
    private final Id serviceId;
    private final LocalDateTime initDateTime;
    private final LocalDateTime endDateTime;
    private final ServiceJob reportedService;

    public Report(Id technicianId, Id serviceId, LocalDateTime initDateTime, LocalDateTime endDateTime, ServiceJob reportedService) {
        Objects.requireNonNull(technicianId, "The technician ID must not be null");
        Objects.requireNonNull(serviceId, "The service ID must not be null");
        Objects.requireNonNull(initDateTime, "The service initial-date-time must not be null");
        Objects.requireNonNull(endDateTime, "The service end-date-time must not be null");
        Objects.requireNonNull(reportedService, "The service must not be null");

        if(endDateTime.isBefore(initDateTime)){
            throw new IllegalArgumentException("The service end-date-time must be after the initial one");
        }

        this.technicianId = technicianId;
        this.serviceId = serviceId;
        this.initDateTime = initDateTime;
        this.endDateTime = endDateTime;
        this.reportedService = reportedService;
    }
}
