package com.example.handyman.calculator.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TechnicianTest {

    Id technicianId = Id.generateUUID();
    Name name = new Name("Mateo");
    LastName lastName = new LastName("Baena");

    Id serviceId = Id.generateUUID();
    ServiceType type = ServiceType.Normal;
    LocalDateTime initDateTime = LocalDateTime.now();

    Id reportId = Id.generateUUID();
    LocalDateTime endDateTime = initDateTime.plusDays(1);

    ServiceJob service = new ServiceJob(serviceId, type);
    Report report = new Report(technicianId, serviceId, initDateTime, endDateTime);

    List<Report> reports = new ArrayList<>();

    @Test
    public void shouldReturnNullPointerExceptionWhenTheTechnicianIDisNull(){
        //Arrange
        technicianId = null;

        //Act
        Executable executable = () -> new Technician(technicianId, name, lastName, reports);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldReturnNullPointerExceptionWhenTheTechnicianNameIsNull(){
        //Arrange
        name = null;

        //Act
        Executable executable = () -> new Technician(technicianId, name, lastName, reports);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldReturnNullPointerExceptionWhenTheTechnicianLastNameIsNull(){
        //Arrange
        lastName = null;

        //Act
        Executable executable = () -> new Technician(technicianId, name, lastName, reports);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldReturnNullPointerExceptionWhenTheTechnicianReportsAreNull(){
        //Arrange
        reports = null;

        //Act
        Executable executable = () -> new Technician(technicianId, name, lastName, reports);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldIncrementTheSizeOfTheReportsWhenANewOneIsAdded(){
        //Arrange
        Id serviceId2 = Id.generateUUID();
        LocalDateTime initDateTime2 = LocalDateTime.now().plusMinutes(2);
        LocalDateTime endDateTime2 = LocalDateTime.now().plusMinutes(4);

        ServiceJob service2 = new ServiceJob(serviceId2, ServiceType.Emergency);

        Report report2 = new Report(technicianId, serviceId2,initDateTime2, endDateTime2);
        Technician technician = new Technician(technicianId, name, lastName, reports);

        //Act
        technician.addReport(report);
        technician.addReport(report2);

        //Assert
        assertEquals(2, technician.getReports().size());
    }

    @Test
    public void shouldDecreaseTheSizeOfTheReportsWhenANewOneIsRemoved(){
        //Arrange
        Id reportId2 = Id.generateUUID();
        Id serviceId2 = Id.generateUUID();
        LocalDateTime initDateTime2 = LocalDateTime.now().plusMinutes(2);
        LocalDateTime endDateTime2 = LocalDateTime.now().plusMinutes(4);
        ServiceJob service2 = new ServiceJob(serviceId2, ServiceType.Emergency);

        Report report2 = new Report(technicianId, serviceId2, initDateTime2, endDateTime2);
        Technician technician = new Technician(technicianId, name, lastName, reports);

        //Act
        technician.addReport(report);
        technician.addReport(report2);
        technician.deleteReport(report);

        //Assert
        assertEquals(1, technician.getReports().size());
    }
}