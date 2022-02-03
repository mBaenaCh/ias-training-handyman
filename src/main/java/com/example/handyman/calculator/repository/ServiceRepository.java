package com.example.handyman.calculator.repository;

import com.example.handyman.calculator.domain.Id;
import com.example.handyman.calculator.domain.ServiceJob;

import java.util.List;

public interface ServiceRepository {

    void create(ServiceJob service);

    List<ServiceJob> getAll();

    ServiceJob getById(Id id);

    void updateById(Id id, ServiceJob service);

    void deleteById(Id id);

}
