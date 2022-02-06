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

        Long workedMinutesInTheReport;
        Long normalMinutesWorked = 0L;
        Long nightMinutesWorked = 0L;
        Long dominicalMinutesWorked = 0L;
        Long normalExtraMinutesWorked = 0L;
        Long nightExtraMinutesWorked = 0L;
        Long dominicalExtraMinutesWorked = 0L;
        Long totalWorkedHours = 39L;

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

        for (Report report : technician.getReports()) {
            workedMinutesInTheReport = ChronoUnit.HOURS.between(report.getInitDateTime(), report.getEndDateTime());
            //Comparacion DIAS ENTRE LUNES Y SABADO
            if (report.getInitDateTime().getDayOfWeek().compareTo(DayOfWeek.MONDAY) >= 0 && report.getInitDateTime().getDayOfWeek().compareTo(DayOfWeek.SATURDAY) <= 0) {

                //Comparacion de HORAS ENTRE 0 y 7
                if ((0 <= report.getInitDateTime().getHour() && report.getInitDateTime().getHour() <= 7) &&
                        (0 <= report.getEndDateTime().getHour() && report.getEndDateTime().getHour() <= 7)) {
                    nightMinutesWorked += workedMinutesInTheReport;
                    if(totalWorkedHours > 48){
                        nightExtraMinutesWorked += workedMinutesInTheReport;
                    }

                    //Combinacion de horas de trabajo entre nocturas y normales (0 a 7) 0 < initDateTime < 7  & 7 < endDateTime  < 20
                } else if ((0 <= report.getInitDateTime().getHour() && report.getInitDateTime().getHour() <= 7) &&
                        (7 <= report.getEndDateTime().getHour() && report.getEndDateTime().getHour() <= 20)) {
                    nightMinutesWorked += 7 - (long) report.getInitDateTime().getHour();
                    normalMinutesWorked += (long) report.getEndDateTime().getHour() - 7;

                    if(totalWorkedHours > 48){
                        nightExtraMinutesWorked += 7 - (long) report.getInitDateTime().getHour();
                        normalExtraMinutesWorked += (long) report.getEndDateTime().getHour() - 7;
                    }

                    //Comparacion de HORAS ENTRE 7 Y 20 7 < initDateTime & endDateTime < 20
                } else if ((7 <= report.getInitDateTime().getHour() && report.getInitDateTime().getHour() <= 20) &&
                        (7 <= report.getEndDateTime().getHour() && report.getEndDateTime().getHour() <= 20)) {
                    normalMinutesWorked += workedMinutesInTheReport;

                    if(totalWorkedHours > 48){
                        normalExtraMinutesWorked += workedMinutesInTheReport;
                    }

                    //Combinacion de horas de trabajo entre normales y nocturnas (20 a 23) 7 < initDateTime < 20  & 20 < endDateTime  < 24
                } else if ((7 <= report.getInitDateTime().getHour() && report.getInitDateTime().getHour() <= 20) &&
                        (20 <= report.getEndDateTime().getHour() && report.getEndDateTime().getHour() <= 24)) {
                    nightMinutesWorked += (long) report.getEndDateTime().getHour() - 20;
                    normalMinutesWorked += 20 - (long) report.getInitDateTime().getHour();

                    if(totalWorkedHours > 48){
                        nightExtraMinutesWorked += (long) report.getEndDateTime().getHour() - 20;
                        normalExtraMinutesWorked += 20 - (long) report.getInitDateTime().getHour();
                    }

                    //Comparacion de HORAS ENTRE 20  Y 24 20 < initDateTime & endDateTime< 24
                } else if ((20 <= report.getInitDateTime().getHour() && report.getInitDateTime().getHour() <= 24) &&
                        (20 <= report.getEndDateTime().getHour() && report.getEndDateTime().getHour() <= 24)) {
                    nightMinutesWorked += workedMinutesInTheReport;

                    if (totalWorkedHours > 48) {
                        nightExtraMinutesWorked += workedMinutesInTheReport;
                    }

                }
                //Horas dominicales
            } else if (report.getInitDateTime().getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0) {
                dominicalMinutesWorked += workedMinutesInTheReport;
                if (totalWorkedHours > 48) {
                    dominicalExtraMinutesWorked += dominicalMinutesWorked;
                }

            }

            totalWorkedHours += workedMinutesInTheReport;
        }
        System.out.println("Total worked hours in the reports: " + totalWorkedHours);
        System.out.println("Total normal hours worked in the reports: " + normalMinutesWorked);
        System.out.println("Total night hours worked in the reports: " + nightMinutesWorked);
        System.out.println("Total dominical hours worked in the reports: " + dominicalMinutesWorked);
        System.out.println("Total normal extra hours worked in the reports: " + normalExtraMinutesWorked);
        System.out.println("Total night extra hours worked in the reports: " + nightExtraMinutesWorked);
        System.out.println("Total dominical extra hours worked in the reports: " + dominicalExtraMinutesWorked);
    }
}