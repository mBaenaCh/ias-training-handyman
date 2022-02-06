package com.example.handyman.calculator.domain;


import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.IsoFields;
import java.util.Objects;

@Getter
public class Report {

    private final Id technicianId;
    private final Id serviceId;
    private final LocalDateTime initDateTime;
    private final LocalDateTime endDateTime;
    private final Integer weekOfYear;

    public Report(Id technicianId, Id serviceId, LocalDateTime initDateTime, LocalDateTime endDateTime) {
        Objects.requireNonNull(technicianId, "The technician ID must not be null");
        Objects.requireNonNull(serviceId, "The service ID must not be null");
        Objects.requireNonNull(initDateTime, "The service initial-date-time must not be null");
        Objects.requireNonNull(endDateTime, "The service end-date-time must not be null");

        if(endDateTime.isBefore(initDateTime)){
            throw new IllegalArgumentException("The service end-date-time must be after the initial one");
        }

        if(initDateTime.getYear() != endDateTime.getYear() ||
                initDateTime.getMonth().compareTo(endDateTime.getMonth()) != 0 ||
                    initDateTime.getDayOfMonth() != endDateTime.getDayOfMonth()){

            throw new IllegalArgumentException("The service has to be done in the same day, week, month and year");

        }

        this.technicianId = technicianId;
        this.serviceId = serviceId;
        this.initDateTime = initDateTime;
        this.endDateTime = endDateTime;
        this.weekOfYear = initDateTime.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
    }
}
