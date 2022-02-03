package com.example.handyman.calculator.repository;

import com.example.handyman.calculator.domain.Id;
import com.example.handyman.calculator.domain.ServiceJob;
import com.example.handyman.calculator.domain.ServiceType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceJDBCRepository implements ServiceRepository {

    private final JdbcTemplate jdbcTemplate;

    public ServiceJDBCRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ServiceJob> serviceRowMapper = (resultSet, rowNum) -> {
        Id serviceId = Id.generateUUIDFromString(resultSet.getString("id"));

        if(resultSet.getString("type").equals("Normal")){
            ServiceType type = ServiceType.Normal;
            return new ServiceJob(serviceId, type);
        }else{
            ServiceType type = ServiceType.Emergency;
            return new ServiceJob(serviceId, type);
        }
    };

    @Override
    public void create(ServiceJob service) {
        String query = "INSERT INTO service(id, type) VALUES(?, ?)";

        jdbcTemplate.update(query, ps -> {
            ps.setString(1, service.getServiceId().toString());
            ps.setString(2, service.getType().toString());
        });
    }

    @Override
    public List<ServiceJob> getAll() {
        String query = "SELECT * FROM service";
        return jdbcTemplate.query(query, serviceRowMapper);
    }

    @Override
    public ServiceJob getById(Id id) {
        String query = "SELECT * FROM service WHERE service.id = ?";
        return jdbcTemplate.queryForObject(query, serviceRowMapper, id.toString());
    }

    @Override
    public void updateById(Id id, ServiceJob service) {
        String query = "UPDATE service SET id = ?, type = ?";
        jdbcTemplate.update(query,
                id.toString(),
                service.getType().toString());
    }

    @Override
    public void deleteById(Id id) {
        String query = "DELETE FROM service WHERE id = ?";
        jdbcTemplate.update(query, id.toString());
    }
}
