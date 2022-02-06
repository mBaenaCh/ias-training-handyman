package com.example.handyman.calculator.controller;

import com.example.handyman.calculator.domain.*;
import com.example.handyman.calculator.model.ReportInput;
import com.example.handyman.calculator.model.WorkedHoursInput;
import com.example.handyman.calculator.service.ReportService;
import com.example.handyman.calculator.service.ServiceJobService;
import com.example.handyman.calculator.service.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(value = "/api/reports")
public class ReportController {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private ReportService service;

    @Autowired
    private ServiceJobService serviceJobService;

    @Autowired
    private TechnicianService technicianService;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Report> create(
            @RequestBody ReportInput input) {

        Id technicianId = Id.generateUUIDFromString(input.getTechnicianId());
        Id serviceId = Id.generateUUIDFromString(input.getServiceId());
        LocalDateTime initDateTime = LocalDateTime.parse(input.getInitDateTime(), formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(input.getEndDateTime(), formatter);

        ServiceJob serviceJob = serviceJobService.getById(serviceId);
        Technician technician = technicianService.getById(technicianId);

        if (serviceJob != null && technician != null) {
            Report toCreate = new Report(technicianId,
                    serviceId,
                    initDateTime,
                    endDateTime);

            return new ResponseEntity<>(
                    service.create(toCreate),
                    HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping
    public ResponseEntity<List<Report>> getAll(){

        return new ResponseEntity<>(
                service.getAll(),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<WorkedHours> getWorkedHoursByTechnicianAndWeek(
            @RequestBody WorkedHoursInput input){

        Id technicianId = Id.generateUUIDFromString(input.getTechnicianId());
        return new ResponseEntity<>(
                service.calculateHoursOfWorkForTechnician(input.getWeek(), technicianId),
                HttpStatus.OK);
    }

}
