package com.example.handyman.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportInput {
    private String technicianId;
    private String serviceId;
    private String initDateTime;
    private String endDateTime;
}
