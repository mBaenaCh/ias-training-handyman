package com.example.handyman.calculator.domain;


import lombok.Getter;

import java.util.Objects;

@Getter
public class Service {
    private final Id serviceId;
    private final ServiceType type;

    public Service(Id serviceId, ServiceType type) {
        Objects.requireNonNull(serviceId, "The ID must not be null");
        Objects.requireNonNull(type, "The service type must not be null");

        this.serviceId = serviceId;
        this.type = type;
    }
}
