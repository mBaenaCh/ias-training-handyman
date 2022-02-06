package com.example.handyman.calculator.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    //Report report = new Report(technicianId, serviceId, initDateTime, endDateTime);

    List<Report> reports = new ArrayList<>();

    @Test
    public void shouldReturnNullPointerExceptionWhenTheTechnicianIDisNull() {
        //Arrange
        technicianId = null;

        //Act
        Executable executable = () -> new Technician(technicianId, name, lastName, reports);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldReturnNullPointerExceptionWhenTheTechnicianNameIsNull() {
        //Arrange
        name = null;

        //Act
        Executable executable = () -> new Technician(technicianId, name, lastName, reports);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldReturnNullPointerExceptionWhenTheTechnicianLastNameIsNull() {
        //Arrange
        lastName = null;

        //Act
        Executable executable = () -> new Technician(technicianId, name, lastName, reports);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldReturnNullPointerExceptionWhenTheTechnicianReportsAreNull() {
        //Arrange
        reports = null;

        //Act
        Executable executable = () -> new Technician(technicianId, name, lastName, reports);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldIncrementTheSizeOfTheReportsWhenANewOneIsAdded() {
        //Arrange
        Id serviceId2 = Id.generateUUID();
        LocalDateTime initDateTime2 = LocalDateTime.now().plusMinutes(2);
        LocalDateTime endDateTime2 = LocalDateTime.now().plusMinutes(4);

        ServiceJob service2 = new ServiceJob(serviceId2, ServiceType.Emergency);

        Report report2 = new Report(technicianId, serviceId2, initDateTime2, endDateTime2);
        Technician technician = new Technician(technicianId, name, lastName, reports);

        //Act
        //technician.addReport(report);
        technician.addReport(report2);

        //Assert
        assertEquals(2, technician.getReports().size());
    }

    @Test
    public void shouldDecreaseTheSizeOfTheReportsWhenANewOneIsRemoved() {
        //Arrange
        Id reportId2 = Id.generateUUID();
        Id serviceId2 = Id.generateUUID();
        LocalDateTime initDateTime2 = LocalDateTime.now().plusMinutes(2);
        LocalDateTime endDateTime2 = LocalDateTime.now().plusMinutes(4);
        ServiceJob service2 = new ServiceJob(serviceId2, ServiceType.Emergency);

        Report report2 = new Report(technicianId, serviceId2, initDateTime2, endDateTime2);
        Technician technician = new Technician(technicianId, name, lastName, reports);

        //Act
        //technician.addReport(report);
        technician.addReport(report2);
        //technician.deleteReport(report);

        //Assert
        assertEquals(1, technician.getReports().size());
    }

    @Test
    public void shouldCalculateDifferentTypesOfHours() {

        Report report2 = new Report(
                Id.generateUUID(),
                Id.generateUUID(),
                LocalDateTime.of(2022, 2, 5, 5, 0, 0),
                LocalDateTime.of(2022, 2, 5, 7, 0, 0));

        Report report3 = new Report(
                Id.generateUUID(),
                Id.generateUUID(),
                LocalDateTime.of(2022, 2, 5, 21, 0, 0),
                LocalDateTime.of(2022, 2, 5, 23, 0, 0)
        );

        Report report4 = new Report(
                Id.generateUUID(),
                Id.generateUUID(),
                LocalDateTime.of(2022, 2, 7, 5, 0, 0),
                LocalDateTime.of(2022, 2, 7, 12, 0, 0)
        );

        Report report5 = new Report(
                Id.generateUUID(),
                Id.generateUUID(),
                LocalDateTime.of(2022, 2, 8, 14, 0, 0),
                LocalDateTime.of(2022, 2, 8, 21, 0, 0)
        );

        //reports.add(report);
        reports.add(report2);
        reports.add(report3);
        reports.add(report4);
        reports.add(report5);

        Technician technician = new Technician(technicianId, name, lastName, reports);

        WorkedHours workedHours = technician.calculateHoursWorked(technician.getReports());

        assertEquals(18, workedHours.getTotalHours());
        assertEquals(11, workedHours.getNormalHours());
        assertEquals(7, workedHours.getNightHours());
        assertEquals(0, workedHours.getDominicalHours());
    }
}