package com.example.handyman.calculator.service;


import com.example.handyman.calculator.domain.Id;
import com.example.handyman.calculator.domain.Report;
import com.example.handyman.calculator.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

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

}
