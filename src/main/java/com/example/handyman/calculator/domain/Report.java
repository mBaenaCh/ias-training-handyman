package com.example.handyman.calculator.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class Report {

    private final Id reportId;
    private final Id technicianId;
    private final Id serviceId;
    private final LocalDateTime initDateTime;
    private final LocalDateTime endDateTime;

    public Report(Id reportId, Id technicianId, Id serviceId, LocalDateTime initDateTime, LocalDateTime endDateTime) {
        Objects.requireNonNull(reportId, "The ID must not be null");
        Objects.requireNonNull(technicianId, "The technician ID must not be null");
        Objects.requireNonNull(serviceId, "The service ID must not be null");
        Objects.requireNonNullElse(initDateTime, "The service initial-date-time must not be null");
        Objects.requireNonNullElse(endDateTime, "The service end-date-time must not be null");

        this.reportId = reportId;
        this.technicianId = technicianId;
        this.serviceId = serviceId;
        this.initDateTime = initDateTime;
        this.endDateTime = endDateTime;
    }
}
