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
    LocalDateTime initDateTime = LocalDateTime.of(2022, 2, 17, 10, 0, 0);

    Id reportId = Id.generateUUID();
    LocalDateTime endDateTime = LocalDateTime.of(2022, 2, 17, 15, 0, 0);

    Report report = new Report(reportId, serviceId, initDateTime, endDateTime);

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
        Technician technician = new Technician(technicianId, name, lastName, reports);

        //Act
        technician.addReport(report);

        //Assert
        assertEquals(1, technician.getReports().size());
    }

    @Test
    public void shouldDecreaseTheSizeOfTheReportsWhenANewOneIsRemoved() {
        //Arrange
        Technician technician = new Technician(technicianId, name, lastName, reports);

        //Act
        technician.addReport(report);
        technician.deleteReport(report);

        //Assert
        assertEquals(0, technician.getReports().size());
    }

    @Test
    public void shouldCalculateDifferentTypesOfHours() {
        //Arrange
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

        //Act
        reports.add(report2);
        reports.add(report3);
        reports.add(report4);
        reports.add(report5);

        Technician technician = new Technician(technicianId, name, lastName, reports);

        WorkedHours workedHours = technician.calculateHoursWorked(technician.getReports());

        //Assert
        assertEquals(18, workedHours.getTotalHours());
        assertEquals(11, workedHours.getNormalHours());
        assertEquals(7, workedHours.getNightHours());
        assertEquals(0, workedHours.getDominicalHours());
    }
}