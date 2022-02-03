package com.example.handyman.calculator.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class ServiceJob {

    private final Id serviceId;
    private final ServiceType type;

    public ServiceJob(Id serviceId, ServiceType type) {
        Objects.requireNonNull(serviceId, "The service Id can not be null");
        Objects.requireNonNull(type, "The service type can not be null");

        this.serviceId = serviceId;
        this.type = type;
    }
}
