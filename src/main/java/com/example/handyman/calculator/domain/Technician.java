package com.example.handyman.calculator.domain;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class Technician {
    private final Id technicianId;
    private final Name name;
    private final LastName lastName;
    private final List<Report> reports;

    public Technician(Id technicianId, Name name, LastName lastName, List<Report> reports){
        Objects.requireNonNull(technicianId, "The ID must not be null");
        Objects.requireNonNull(name, "The technician name must not be null");
        Objects.requireNonNull(lastName, "The technician last name must not be null");
        Objects.requireNonNull(reports, "The reports list must not be null");

        this.technicianId = technicianId;
        this.name = name;
        this.lastName = lastName;
        this.reports = reports;
    }

    public void addReport(Report report){
        this.reports.add(report);
    }

    public void deleteReport(Report report){
        this.reports.remove(report);
    }
}
