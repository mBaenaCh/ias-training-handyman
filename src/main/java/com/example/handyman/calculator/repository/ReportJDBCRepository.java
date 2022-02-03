package com.example.handyman.calculator.repository;

import com.example.handyman.calculator.domain.Id;
import com.example.handyman.calculator.domain.Report;
import com.example.handyman.calculator.domain.ServiceJob;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReportJDBCRepository implements ReportRepository{

    private final JdbcTemplate jdbcTemplate;
    private ServiceRepository serviceRepository;

    public ReportJDBCRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Report> reportRowMapper = (resultSet, rowNum) -> {
        Id technicianId = Id.generateUUIDFromString(resultSet.getString("technician_id"));
        Id serviceId = Id.generateUUIDFromString(resultSet.getString("service_id"));
        LocalDateTime initDateTime = resultSet.getTimestamp("init_date_time").toLocalDateTime();
        LocalDateTime endDateTime = resultSet.getTimestamp("end_date_time").toLocalDateTime();
        ServiceJob reportedService = serviceRepository.getById(serviceId);
        return new Report(
                technicianId,
                serviceId,
                initDateTime,
                endDateTime,
                reportedService
        );
    };

}
