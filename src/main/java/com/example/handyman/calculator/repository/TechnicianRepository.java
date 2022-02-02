package com.example.handyman.calculator.repository;

import com.example.handyman.calculator.domain.Id;
import com.example.handyman.calculator.domain.Technician;

import java.util.List;

public interface TechnicianRepository {

    void create(Technician technician);

    List<Technician> getAll();

    Technician getById(Id id);

    void updateById(Id id, Technician technician);

    void deleteById(Id id);
}
