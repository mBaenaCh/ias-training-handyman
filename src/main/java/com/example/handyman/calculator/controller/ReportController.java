package com.example.handyman.calculator.controller;

import com.example.handyman.calculator.domain.Id;
import com.example.handyman.calculator.domain.Report;
import com.example.handyman.calculator.domain.ServiceJob;
import com.example.handyman.calculator.model.ReportInput;
import com.example.handyman.calculator.service.ReportService;
import com.example.handyman.calculator.service.ServiceJobService;
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
    private ServiceJobService jobService;

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
        ServiceJob serviceJob = jobService.getById(serviceId);

        if (serviceJob != null) {
            Report toCreate = new Report(technicianId,
                    serviceId,
                    initDateTime,
                    endDateTime,
                    serviceJob);

            return new ResponseEntity<>(toCreate, HttpStatus.CREATED);
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

}
