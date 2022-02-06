package com.example.handyman.calculator.controller;

import com.example.handyman.calculator.domain.Id;
import com.example.handyman.calculator.domain.ServiceJob;
import com.example.handyman.calculator.domain.ServiceType;
import com.example.handyman.calculator.model.ServiceJobInput;
import com.example.handyman.calculator.service.ServiceJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/api/services")
public class ServiceJobController {

    private ServiceJobService serviceJobService;

    public ServiceJobController(ServiceJobService serviceJobService) {
        this.serviceJobService = serviceJobService;
    }

    @PostMapping
    public ResponseEntity<ServiceJob> create(
            @RequestBody ServiceJobInput input) {

        Id serviceId = Id.generateUUID();

        if (input.getType().trim().toLowerCase().equals("normal")) {
            ServiceJob serviceJob = new ServiceJob(serviceId, ServiceType.Normal);
            return new ResponseEntity<>(
                    serviceJobService.create(serviceJob),
                    HttpStatus.CREATED);

        } else if (input.getType().trim().toLowerCase().equals("emergency")) {

            ServiceJob serviceJob = new ServiceJob(serviceId, ServiceType.Emergency);
            return new ResponseEntity<>(
                    serviceJobService.create(serviceJob),
                    HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ServiceJob>> getAll() {
        return new ResponseEntity<>(serviceJobService.getAll(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceJob> getById(
            @PathVariable("id") String id) {

        Id serviceId = Id.generateUUIDFromString(id);
        ServiceJob foundService = serviceJobService.getById(serviceId);
        if (foundService != null) {
            return new ResponseEntity<>(foundService,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(
            @PathVariable("id") String id) {

        Id serviceId = Id.generateUUIDFromString(id);

        if (serviceJobService.deleteById(serviceId)) {
            return new ResponseEntity<>(
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND);
        }
    }
}
