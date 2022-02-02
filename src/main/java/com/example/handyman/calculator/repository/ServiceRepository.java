package com.example.handyman.calculator.repository;

import com.example.handyman.calculator.domain.Id;
import com.example.handyman.calculator.domain.Report;
import com.example.handyman.calculator.domain.Service;
import com.example.handyman.calculator.domain.Technician;

import java.util.List;

public interface ServiceRepository {

    void create(Service service);

    List<Service> getAll();

    Service getById(Id id);

    void updateById(Id id, Service service);

    void deleteById(Id id);

}
