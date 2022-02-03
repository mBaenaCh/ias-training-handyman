package com.example.handyman.calculator.controller;

import com.example.handyman.calculator.domain.Id;
import com.example.handyman.calculator.domain.ServiceJob;
import com.example.handyman.calculator.service.ServiceJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/services")
public class ServiceJobController {

    private ServiceJobService serviceJobService;

    public ServiceJobController(ServiceJobService serviceJobService){
        this.serviceJobService = serviceJobService;
    }

    @PostMapping
    public ResponseEntity<ServiceJob> create(
            @RequestBody ServiceJob service){
        return new ResponseEntity<>(
                serviceJobService.create(service),
                HttpStatus.CREATED);
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
        if(foundService != null){
            return new ResponseEntity<>(foundService,
                    HttpStatus.OK);
        }else {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceJob> updateById(
            @PathVariable("id") String id,
            @RequestBody ServiceJob service){

        Id serviceId = Id.generateUUIDFromString(id);

        if(serviceJobService.updateById(serviceId, service)){
            return new ResponseEntity<>(
                    service,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(
            @PathVariable("id") String id){

        Id serviceId = Id.generateUUIDFromString(id);

        if(serviceJobService.deleteById(serviceId)){
            return new ResponseEntity<>(
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND);
        }
    }
}
