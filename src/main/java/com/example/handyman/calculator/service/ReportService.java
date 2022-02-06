package com.example.handyman.calculator.service;

import com.example.handyman.calculator.domain.Id;
import com.example.handyman.calculator.domain.Report;
import com.example.handyman.calculator.domain.Technician;
import com.example.handyman.calculator.domain.WorkedHours;
import com.example.handyman.calculator.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private TechnicianService technicianService;

    private ReportRepository repository;

    public ReportService(ReportRepository repository){
        this.repository = repository;
    }

    public Report create(Report report){
        repository.create(report);
        return report;
    }

    public List<Report> getAll(){
        return repository.getAll();
    }

    public WorkedHours calculateHoursOfWorkForTechnician(Integer week, Id id){

        Technician technician = technicianService.getById(id);

        List<Report> reports = repository.getAllFromTechnicianByWeek(week, id);

        WorkedHours workedHours = technician.calculateHoursWorked(reports);

        return workedHours;
    }

}
