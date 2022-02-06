package com.example.handyman.calculator.domain;

import lombok.Getter;

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
        this.totalHours = totalHours;
        this.normalHours = normalHours;
        this.nightHours = nightHours;
        this.dominicalHours = dominicalHours;
        this.normalExtraHours = normalExtraHours;
        this.nightExtraHours = nightExtraHours;
        this.dominicalExtraHours = dominicalExtraHours;
    }
}
