package com.example.handyman.calculator.service;

import com.example.handyman.calculator.domain.Id;
import com.example.handyman.calculator.domain.Technician;
import com.example.handyman.calculator.repository.TechnicianRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicianService {

    private TechnicianRepository repository;

    public TechnicianService(TechnicianRepository repository){
        this.repository = repository;
    }

    public Technician create(Technician technician){
        repository.create(technician);
        return technician;
    }

    public List<Technician> getAll(){
        return repository.getAll();
    }

    public Technician getById(Id id){
        return repository.getById(id);
    }

    public Boolean updateById(Id id, Technician technician){
        Technician foundTechnician = repository.getById(id);
        if(foundTechnician != null){
            repository.updateById(id, technician);
            return true;
        }else {
            return false;
        }
    }

    public Boolean deleteById(Id id){
        Technician foundTechnician = repository.getById(id);
        if(foundTechnician != null){
            repository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
