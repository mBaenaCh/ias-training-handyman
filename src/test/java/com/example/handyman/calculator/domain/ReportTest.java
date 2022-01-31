package com.example.handyman.calculator.domain;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {
    Id reportId = Id.generateUUID();
    Id serviceId = Id.generateUUID();
    Id technicianId = Id.generateUUID();
    LocalDateTime initDateTime = LocalDateTime.now();
    LocalDateTime endDateTime = LocalDateTime.now().plusMinutes(2);

    @Test
    public void shouldReturnNullPointerExceptionWhenTheReportIDisNull(){
        //Arrange
        reportId = null;

        //Act
        Executable executable = () -> new Report(reportId, serviceId, technicianId, initDateTime, endDateTime);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldReturnNullPointerExceptionWhenTheTechnicianIDisNull(){
        //Arrange
        reportId = null;

        //Act
        Executable executable = () -> new Report(reportId, technicianId, serviceId, initDateTime, endDateTime);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldReturnNullPointerExceptionWhenTheServiceIDisNull(){
        //Arrange
        serviceId = null;

        //Act
        Executable executable = () -> new Report(reportId, technicianId, serviceId, initDateTime, endDateTime);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldReturnNullPointerExceptionWhenTheInitDateTimeIsNull(){
        //Arrange
        initDateTime = null;

        //Act
        Executable executable = () -> new Report(reportId, technicianId, serviceId, initDateTime, endDateTime);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldReturnNullPointerExceptionWhenTheEndDateTimeIsNull(){
        //Arrange
        endDateTime = null;

        //Act
        Executable executable = () -> new Report(reportId, technicianId, serviceId, initDateTime, endDateTime);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldReturnIllegalArgumentExceptionWhenTheEndDateTimeIsBeforeTheInitDateTime(){
        //Arrange
        endDateTime = LocalDateTime.now().minusMinutes(2);

        //Act
        Executable executable = () -> new Report(reportId, technicianId, serviceId, initDateTime, endDateTime);

        //Assert
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    public void formattingLocalDateTime(){

        LocalDateTime init = LocalDateTime.of(2022, 1, 18, 8, 0, 0);
        LocalDateTime end = LocalDateTime.of(2022, 1, 31, 15, 59, 0);

        long workedHours = ChronoUnit.HOURS.between(init, end);

        DayOfWeek initDay = init.getDayOfWeek(); //Tuestay < Saturday


        if(init.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) <= 0){
            System.out.println(initDay + " menor o igual que " + DayOfWeek.SATURDAY);
        }else {
            System.out.println(initDay + " Mayor que " + DayOfWeek.SATURDAY);
        }

        Integer week = init.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        System.out.println("Amount of hours worked: "+ workedHours);
        System.out.println("Number of the week for the given date: "+ week);
        System.out.println("Day of week: "+ initDay);
        System.out.println("Hora de la fecha: "+ init.getHour());
        assertEquals(3, week);
    }

    @Test
    public void shouldCreateMultipleReports(){
        List<Report> reports = new ArrayList<>();
        Integer i=1;
        while(i < 6){
            Id reportId = Id.generateUUID();
            Id technicianId = Id.generateUUID();
            Id serviceId = Id.generateUUID();
            LocalDateTime initDate = LocalDateTime.of(2022, 1, 2*i, i, 0, 0);
            System.out.println("init date: "+initDate);
            LocalDateTime endDate = LocalDateTime.of(2022, 1, 2*i, 3*i, 0, 0);
            System.out.println("end date: "+endDate);
            Report report = new Report(reportId, technicianId, serviceId, initDate, endDate);
            reports.add(report);
            i++;
        }

        System.out.println(reports.size());
    }
}