package com.example.handyman.calculator.controller;

import com.example.handyman.calculator.domain.*;
import com.example.handyman.calculator.model.TechnicianInput;
import com.example.handyman.calculator.service.TechnicianService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/technicians")
public class TechnicianController {

    private TechnicianService service;

    public TechnicianController(TechnicianService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Technician> create(
            @RequestBody TechnicianInput input){

        Id id = Id.generateUUIDFromString(input.getId());
        Name name = new Name(input.getName());
        LastName lastName = new LastName(input.getLastName());
        List<Report> reports = new ArrayList<>();

        Technician toCreate = new Technician(
                id,
                name,
                lastName,
                reports
        );

        return new ResponseEntity<>(
                service.create(toCreate),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Technician>> getAll(){
        return new ResponseEntity<>(
                service.getAll(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technician> getById(
            @PathVariable("id") String id){

        Id technicianId = Id.generateUUIDFromString(id);
        Technician foundTechnician = service.getById(technicianId);
        if(foundTechnician != null){
            return new ResponseEntity<>(
                    foundTechnician,
                    HttpStatus.OK);
        }else{
            return new ResponseEntity(
                    "The technician with the given ID was not found",
                    HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Technician> updateById(
            @PathVariable("id") String id,
            @RequestBody TechnicianInput input){

        Id technicianId = Id.generateUUIDFromString(id);
        Name name = new Name(input.getName());
        LastName lastName = new LastName(input.getLastName());
        List<Report> reports = service.getAllReports(technicianId);

        Technician technician = new Technician(technicianId, name, lastName, reports);

        if(service.updateById(technicianId, technician)){
            return new ResponseEntity<>(
                    technician,
                    HttpStatus.OK);
        }else{
            return new ResponseEntity(
                    "The technician with the given ID was not found",
                    HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteById(
            @PathVariable("id") String id){

        Id technicianId = Id.generateUUIDFromString(id);

        if(service.deleteById(technicianId)){
            return  new ResponseEntity(
                    "Deleted succesfuly",
                    HttpStatus.OK);
        }else{
            return new ResponseEntity(
                    "The technician with the given ID was not found",
                    HttpStatus.NOT_FOUND);
        }
    }
}
