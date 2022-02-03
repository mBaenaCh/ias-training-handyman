package com.example.handyman.calculator.repository;

import com.example.handyman.calculator.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TechnicianJDBCRepository implements TechnicianRepository {


    private final JdbcTemplate jdbcTemplate;
    private ServiceJDBCRepository serviceRepository;

    public TechnicianJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Technician> technicianRowMapper = (resultSet, rowNum) -> {
        Id id = Id.generateUUIDFromString(resultSet.getString("id"));
        Name name = new Name(resultSet.getString("name"));
        LastName lastName = new LastName(resultSet.getString("last_name"));
        List<Report> reports = getAllReports(id);
        return new Technician(
                id,
                name,
                lastName,
                reports
        );
    };

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
    public void create(Technician technician) {
        String query = "INSERT INTO technician(id, name, last_name) VALUES(?, ?, ?)";

        jdbcTemplate.update(query, ps -> {
           ps.setString(1, technician.getTechnicianId().toString());
           ps.setString(2, technician.getName().toString());
           ps.setString(3, technician.getLastName().toString());
        });
    }

    @Override
    public List<Technician> getAll() {
        String query = "SELECT * FROM technician";
        return jdbcTemplate.query(query, technicianRowMapper);
    }

    @Override
    public Technician getById(Id id) {
        String query = "SELECT * FROM technician WHERE id = ?";
        return jdbcTemplate.queryForObject(query, technicianRowMapper, id.toString());
    }

    @Override
    public void updateById(Id id, Technician technician) {
        String query = "UPDATE technician SET id = ?, name = ?, last_name = ? WHERE id = ?";
        jdbcTemplate.update(query,
                id.toString(),
                technician.getName().toString(),
                technician.getLastName().toString(),
                id.toString());
    }

    @Override
    public void deleteById(Id id) {
        String query = "DELETE FROM technician WHERE id = ?";
        jdbcTemplate.update(query, id.toString());
    }

    @Override
    public List<Report> getAllReports(Id technicianId){
        String query = "SELECT technician_id, service_id, init_date_time, end_date_time FROM report INNER JOIN technician ON report.technician_id = technician.id AND report.technician_id = ?";
        return jdbcTemplate.query(query, reportRowMapper, technicianId.toString());
    }
}
