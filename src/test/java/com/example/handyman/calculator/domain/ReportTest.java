package com.example.handyman.calculator.domain;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;

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
    /*
    @Test
    public void formattingLocalDateTime() {

        LocalDateTime init = LocalDateTime.of(2022, 1, 18, 8, 0, 0);
        LocalDateTime end = LocalDateTime.of(2022, 1, 31, 15, 59, 0);

        long workedHours = ChronoUnit.HOURS.between(init, end);

        DayOfWeek initDay = init.getDayOfWeek(); //Tuestay < Saturday


        if (init.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) <= 0) {
            System.out.println(initDay + " menor o igual que " + DayOfWeek.SATURDAY);
        } else {
            System.out.println(initDay + " Mayor que " + DayOfWeek.SATURDAY);
        }

        Integer week = init.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        System.out.println("Amount of hours worked: " + workedHours);
        System.out.println("Number of the week for the given date: " + week);
        System.out.println("Day of week: " + initDay);
        System.out.println("Hora de la fecha: " + init.getHour());
        assertEquals(3, week);
    }*/

    /*@Test
    public void shouldClassifyAReportDateIntervalToATypeOfHours() {
        LocalDateTime initTime = LocalDateTime.of(2022, 2, 3, 22, 26);
        LocalDateTime endTime = LocalDateTime.of(2022, 2, 3, 22, 30);

        Integer weekOfTheYear = initTime.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        LocalTime initShift = LocalTime.of(7,0);
        LocalTime endShift = LocalTime.of(20,0);*/

        /*LocalDateTime initTimeNormalNight = LocalDateTime.of(2022, 2, 5, 21, 0);
        LocalDateTime endTimeNormalNight = LocalDateTime.of(2022, 2, 5, 22, 0);

        LocalDateTime initTimeDominical = LocalDateTime.of(2022, 2, 6, 6, 28);
        LocalDateTime endTimeDominical = LocalDateTime.of(2022, 2, 6, 8, 28);*/

        //Long workedHours = ChronoUnit.MINUTES.between(initTime, endTime);

        /*
        Set<DayOfWeek> daysOpen = new HashSet<>(Arrays.asList(
                DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));*/

        /*
        if( initTime.getDayOfWeek().compareTo(DayOfWeek.MONDAY) >= 0 && initTime.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) <= 0){

            if( initTime.toLocalTime().isAfter(initShift) && initTime.toLocalTime().isBefore(endShift) ){

                System.out.println("Horas normales: " + ChronoUnit.HOURS.between(initTime, endTime));

            } else {
                System.out.println("Horas nocturnas: " + ChronoUnit.HOURS.between(initTime, endTime));
            }

        } else if ( initTime.getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0){
            System.out.println("Horas dominicales: " + ChronoUnit.HOURS.between(initTime, endTime));
        }*/
        //System.out.println("Fecha inicio: "+ Timestamp.valueOf(initTime)+" Fecha fin: "+ Timestamp.valueOf(endTime)+" Semana del año: "+weekOfTheYear);
    //}
}