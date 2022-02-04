package com.example.handyman.calculator.repository;

import com.example.handyman.calculator.domain.Id;
import com.example.handyman.calculator.domain.Report;
import com.example.handyman.calculator.domain.ServiceJob;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.IsoFields;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

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

    @Override
    public void create(Report report) {
        String query = "INSERT INTO report(technician_id, service_id, init_date_time, end_date_time, week_of_report) VALUES(?, ?, ?, ?, ?)";

        jdbcTemplate.update(query, ps -> {
            ps.setString(1, report.getTechnicianId().toString());
            ps.setString(2, report.getServiceId().toString());
            ps.setTimestamp(3, Timestamp.valueOf(report.getInitDateTime()));
            ps.setTimestamp(4, Timestamp.valueOf(report.getEndDateTime()));
            ps.setInt(5, report.getInitDateTime().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
        });
    }

    @Override
    public List<Report> getAll() {
        String query = "SELECT * FROM report";
        return jdbcTemplate.query(query, reportRowMapper);
    }

}
