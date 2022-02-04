package com.example.handyman.calculator.repository;

import com.example.handyman.calculator.domain.Id;
import com.example.handyman.calculator.domain.Report;

import java.util.List;

public interface ReportRepository {

    void create(Report report);

    List<Report> getAll();

}
