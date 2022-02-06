package com.example.handyman.calculator.repository;

import com.example.handyman.calculator.domain.Id;
import com.example.handyman.calculator.domain.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Component
public class ReportJDBCRepository implements ReportRepository{

    public static final Calendar timezoneUTC = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private ServiceRepository serviceRepository;

    public ReportJDBCRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Report> reportRowMapper = (resultSet, rowNum) -> {
        Id technicianId = Id.generateUUIDFromString(resultSet.getString("technician_id"));
        Id serviceId = Id.generateUUIDFromString(resultSet.getString("service_id"));
        Timestamp timestampInit = resultSet.getTimestamp("init_date_time", timezoneUTC);
        Timestamp timestampEnd = resultSet.getTimestamp("end_date_time", timezoneUTC);
        LocalDateTime initDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestampInit.getTime()), ZoneOffset.UTC);
        LocalDateTime endDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestampEnd.getTime()), ZoneOffset.UTC);
        return new Report(
                technicianId,
                serviceId,
                initDateTime,
                endDateTime
        );
    };

    @Override
    public void create(Report report) {
        String query = "INSERT INTO report (technician_id, service_id, init_date_time, end_date_time, week_of_year) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(query, ps -> {
            Timestamp timestampInit = new Timestamp(report.getInitDateTime().toInstant(ZoneOffset.UTC).toEpochMilli());
            Timestamp timestampEnd = new Timestamp(report.getEndDateTime().toInstant(ZoneOffset.UTC).toEpochMilli());
            ps.setString(1, report.getTechnicianId().toString());
            ps.setString(2, report.getServiceId().toString());
            ps.setTimestamp(3, timestampInit, timezoneUTC);
            ps.setTimestamp(4, timestampEnd, timezoneUTC);
            ps.setInt(5, report.getWeekOfYear());
        });
    }

    @Override
    public List<Report> getAllReportsFromAWeekAndATechnician(Integer week, Id id) {
        String query = "SELECT * FROM report WHERE report.week_of_year = ? AND report.technician_id = ?";
        return jdbcTemplate.query(query, reportRowMapper, week, id.toString());
    }
    
}
