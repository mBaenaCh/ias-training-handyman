package com.example.handyman.calculator.service;

import com.example.handyman.calculator.domain.Id;
import com.example.handyman.calculator.domain.ServiceJob;
import com.example.handyman.calculator.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceJobService {

    private ServiceRepository repository;

    public ServiceJobService(ServiceRepository repository) {
        this.repository = repository;
    }

    public ServiceJob create(ServiceJob service) {
        repository.create(service);
        return service;
    }

    public List<ServiceJob> getAll() {
        return repository.getAll();
    }

    public ServiceJob getById(Id id) {
        return repository.getById(id);
    }

    public Boolean updateById(Id id, ServiceJob service) {

        if (repository.getById(id) != null) {
            repository.updateById(id, service);
            return true;
        } else {
            return false;
        }
    }

    public Boolean deleteById(Id id) {
        if (repository.getById(id) != null) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
