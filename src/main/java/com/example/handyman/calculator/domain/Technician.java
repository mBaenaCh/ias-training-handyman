package com.example.handyman.calculator.domain;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class Technician {
    private final Id technicianId;
    private final Name name;
    private final LastName lastName;
    private final List<Service> services;
    private final List<Report> reports;

    public Technician(Id technicianId, Name name, LastName lastName, List<Service> services, List<Report> reports){
        Objects.requireNonNull(technicianId, "The ID must not be null");
        Objects.requireNonNull(name, "The technician name must not be null");
        Objects.requireNonNull(lastName, "The technician last name must not be null");
        Objects.requireNonNull(services, "The services list must not be null");
        Objects.requireNonNull(reports, "The reports list must not be null");

        this.technicianId = technicianId;
        this.name = name;
        this.lastName = lastName;
        this.services = services;
        this.reports = reports;
    }
    
}
