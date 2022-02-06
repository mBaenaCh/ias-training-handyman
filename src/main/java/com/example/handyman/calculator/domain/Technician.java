package com.example.handyman.calculator.domain;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Getter
public class Technician {

    public static final Integer INIT_NORMAL_SHIFT_HOUR = 7;
    public static final Integer END_NORMAL_SHIFT_HOUR = 20;
    public static final Integer INIT_OF_DAY = 0;
    public static final Integer END_OF_DAY = 24;
    public static final Integer MAX_WORKED_HOURS_PER_WEEK = 48;

    private final Id technicianId;
    private final Name name;
    private final LastName lastName;
    private final List<Report> reports;

    public Technician(Id technicianId, Name name, LastName lastName, List<Report> reports){
        Objects.requireNonNull(technicianId, "The ID must not be null");
        Objects.requireNonNull(name, "The technician name must not be null");
        Objects.requireNonNull(lastName, "The technician last name must not be null");
        Objects.requireNonNull(reports, "The reports list must not be null");

        this.technicianId = technicianId;
        this.name = name;
        this.lastName = lastName;
        this.reports = reports;
    }

    public void addReport(Report report){
        this.reports.add(report);
    }

    public void deleteReport(Report report){
        this.reports.remove(report);
    }

    public WorkedHours calculateHoursWorked(List<Report> reports){

        Long workedMinutesInTheReport;
        Long normalMinutesWorked = 0L;
        Long nightMinutesWorked = 0L;
        Long dominicalMinutesWorked = 0L;
        Long normalExtraMinutesWorked = 0L;
        Long nightExtraMinutesWorked = 0L;
        Long dominicalExtraMinutesWorked = 0L;
        Long totalWorkedHours = 0L;

        for (Report report : reports) {
            workedMinutesInTheReport = ChronoUnit.HOURS.between(report.getInitDateTime(), report.getEndDateTime());
            //Comparacion DIAS ENTRE LUNES Y SABADO
            if (report.getInitDateTime().getDayOfWeek().compareTo(DayOfWeek.MONDAY) >= 0 && report.getInitDateTime().getDayOfWeek().compareTo(DayOfWeek.SATURDAY) <= 0) {

                //Comparacion de HORAS ENTRE 0 y 7
                if ((INIT_OF_DAY <= report.getInitDateTime().getHour() && report.getInitDateTime().getHour() <= INIT_NORMAL_SHIFT_HOUR) &&
                        (INIT_OF_DAY <= report.getEndDateTime().getHour() && report.getEndDateTime().getHour() <= INIT_NORMAL_SHIFT_HOUR)) {
                    nightMinutesWorked += workedMinutesInTheReport;
                    if(totalWorkedHours > MAX_WORKED_HOURS_PER_WEEK){
                        nightExtraMinutesWorked += workedMinutesInTheReport;
                    }

                    //Combinacion de horas de trabajo entre nocturas y normales (0 a 7) 0 < initDateTime < 7  & 7 < endDateTime  < 20
                } else if ((INIT_OF_DAY <= report.getInitDateTime().getHour() && report.getInitDateTime().getHour() <= INIT_NORMAL_SHIFT_HOUR) &&
                        (INIT_NORMAL_SHIFT_HOUR <= report.getEndDateTime().getHour() && report.getEndDateTime().getHour() <= END_NORMAL_SHIFT_HOUR)) {
                    nightMinutesWorked += INIT_NORMAL_SHIFT_HOUR - (long) report.getInitDateTime().getHour();
                    normalMinutesWorked += (long) report.getEndDateTime().getHour() - INIT_NORMAL_SHIFT_HOUR;

                    if(totalWorkedHours > MAX_WORKED_HOURS_PER_WEEK){
                        nightExtraMinutesWorked += INIT_NORMAL_SHIFT_HOUR - (long) report.getInitDateTime().getHour();
                        normalExtraMinutesWorked += (long) report.getEndDateTime().getHour() - INIT_NORMAL_SHIFT_HOUR;
                    }

                    //Comparacion de HORAS ENTRE 7 Y 20 7 < initDateTime & endDateTime < 20
                } else if ((INIT_NORMAL_SHIFT_HOUR <= report.getInitDateTime().getHour() && report.getInitDateTime().getHour() <= END_NORMAL_SHIFT_HOUR) &&
                        (INIT_NORMAL_SHIFT_HOUR <= report.getEndDateTime().getHour() && report.getEndDateTime().getHour() <= END_NORMAL_SHIFT_HOUR)) {
                    normalMinutesWorked += workedMinutesInTheReport;

                    if(totalWorkedHours > MAX_WORKED_HOURS_PER_WEEK){
                        normalExtraMinutesWorked += workedMinutesInTheReport;
                    }

                    //Combinacion de horas de trabajo entre normales y nocturnas (20 a 23) 7 < initDateTime < 20  & 20 < endDateTime  < 24
                } else if ((INIT_NORMAL_SHIFT_HOUR <= report.getInitDateTime().getHour() && report.getInitDateTime().getHour() <= END_NORMAL_SHIFT_HOUR) &&
                        (END_NORMAL_SHIFT_HOUR <= report.getEndDateTime().getHour() && report.getEndDateTime().getHour() <= END_OF_DAY)) {
                    nightMinutesWorked += (long) report.getEndDateTime().getHour() - END_NORMAL_SHIFT_HOUR;
                    normalMinutesWorked += END_NORMAL_SHIFT_HOUR - (long) report.getInitDateTime().getHour();

                    if(totalWorkedHours > MAX_WORKED_HOURS_PER_WEEK){
                        nightExtraMinutesWorked += (long) report.getEndDateTime().getHour() - END_NORMAL_SHIFT_HOUR;
                        normalExtraMinutesWorked += END_NORMAL_SHIFT_HOUR - (long) report.getInitDateTime().getHour();
                    }

                    //Comparacion de HORAS ENTRE 20  Y 24 20 < initDateTime & endDateTime< 24
                } else if ((END_NORMAL_SHIFT_HOUR <= report.getInitDateTime().getHour() && report.getInitDateTime().getHour() <= END_OF_DAY) &&
                        (END_NORMAL_SHIFT_HOUR <= report.getEndDateTime().getHour() && report.getEndDateTime().getHour() <= END_OF_DAY)) {
                    nightMinutesWorked += workedMinutesInTheReport;

                    if (totalWorkedHours > MAX_WORKED_HOURS_PER_WEEK) {
                        nightExtraMinutesWorked += workedMinutesInTheReport;
                    }

                }
                //Horas dominicales
            } else if (report.getInitDateTime().getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0) {
                dominicalMinutesWorked += workedMinutesInTheReport;
                if (totalWorkedHours > MAX_WORKED_HOURS_PER_WEEK) {
                    dominicalExtraMinutesWorked += dominicalMinutesWorked;
                }

            }

            totalWorkedHours += workedMinutesInTheReport;
        }

        return new WorkedHours(
                totalWorkedHours,
                normalMinutesWorked,
                nightMinutesWorked,
                dominicalMinutesWorked,
                normalExtraMinutesWorked,
                nightExtraMinutesWorked,
                dominicalExtraMinutesWorked);
    }
}
