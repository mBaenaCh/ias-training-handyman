package com.example.handyman.calculator.domain;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {
    Id serviceId = Id.generateUUID();
    Id technicianId = Id.generateUUID();
    LocalDateTime initDateTime = LocalDateTime.now();
    LocalDateTime endDateTime = LocalDateTime.now().plusMinutes(2);

    @Test
    public void shouldReturnNullPointerExceptionWhenTheTechnicianIDisNull() {
        //Arrange
        technicianId = null;

        //Act
        Executable executable = () -> new Report(technicianId, serviceId, initDateTime, endDateTime);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldReturnNullPointerExceptionWhenTheServiceIDisNull() {
        //Arrange
        serviceId = null;

        //Act
        Executable executable = () -> new Report(technicianId, serviceId, initDateTime, endDateTime);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldReturnNullPointerExceptionWhenTheInitDateTimeIsNull() {
        //Arrange
        initDateTime = null;

        //Act
        Executable executable = () -> new Report(technicianId, serviceId, initDateTime, endDateTime);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldReturnNullPointerExceptionWhenTheEndDateTimeIsNull() {
        //Arrange
        endDateTime = null;

        //Act
        Executable executable = () -> new Report(technicianId, serviceId, initDateTime, endDateTime);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldReturnIllegalArgumentExceptionWhenTheEndDateTimeIsBeforeTheInitDateTime() {
        //Arrange
        endDateTime = LocalDateTime.now().minusMinutes(2);

        //Act
        Executable executable = () -> new Report(technicianId, serviceId, initDateTime, endDateTime);

        //Assert
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    public void shouldReturnIllegalArgumentExceptionWhenTheDatesAreNotInTheSameYear(){
        //Arrange
        LocalDateTime init = LocalDateTime.of(2021, 2, 5, 11, 30, 0);
        LocalDateTime end = LocalDateTime.of(2022, 2, 5, 11, 30, 0);

        //Act
        Executable executable = () -> new Report(technicianId, serviceId, init, end);

        //Assert
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    public void shouldReturnIllegalArgumentExceptionWhenTheDatesAreNotInTheSameMonth(){
        //Arrange
        LocalDateTime init = LocalDateTime.of(2022, 1, 5, 11, 30, 0);
        LocalDateTime end = LocalDateTime.of(2022, 2, 5, 11, 30, 0);

        //Act
        Executable executable = () -> new Report(technicianId, serviceId, init, end);

        //Assert
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    public void shouldReturnIllegalArgumentExceptionWhenTheDatesAreNotInTheSameDayOfTheYearAndMonth(){
        //Arrange
        LocalDateTime init = LocalDateTime.of(2021, 2, 4, 11, 30, 0);
        LocalDateTime end = LocalDateTime.of(2022, 2, 5, 11, 30, 0);

        //Act
        Executable executable = () -> new Report(technicianId, serviceId, init, end);

        //Assert
        assertThrows(IllegalArgumentException.class, executable);
    }
}