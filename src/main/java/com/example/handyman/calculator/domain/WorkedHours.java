package com.example.handyman.calculator.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class WorkedHours {
    private final Long totalHours;
    private final Long normalHours;
    private final Long nightHours;
    private final Long dominicalHours;
    private final Long normalExtraHours;
    private final Long nightExtraHours;
    private final Long dominicalExtraHours;

    public WorkedHours(Long totalHours, Long normalHours, Long nightHours, Long dominicalHours, Long normalExtraHours, Long nightExtraHours, Long dominicalExtraHours) {
        Objects.requireNonNull(totalHours, "Total hours can not be null");
        Objects.requireNonNull(normalHours, "Normal hours can not be null");
        Objects.requireNonNull(nightHours, "Night hours can not be null");
        Objects.requireNonNull(dominicalHours, "Dominical hours can not be null");
        Objects.requireNonNull(normalExtraHours, "Normal extra hours can not be null");
        Objects.requireNonNull(nightExtraHours, "Night extra hours can not be null");
        Objects.requireNonNull(dominicalExtraHours, "Dominical extra hours can not be null");

        this.totalHours = totalHours;
        this.normalHours = normalHours;
        this.nightHours = nightHours;
        this.dominicalHours = dominicalHours;
        this.normalExtraHours = normalExtraHours;
        this.nightExtraHours = nightExtraHours;
        this.dominicalExtraHours = dominicalExtraHours;
    }
}
