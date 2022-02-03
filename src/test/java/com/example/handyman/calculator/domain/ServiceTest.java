package com.example.handyman.calculator.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import static org.junit.jupiter.api.Assertions.*;



class ServiceTest {

    Id serviceId = Id.generateUUID();
    ServiceType type = ServiceType.Normal;

    @Test
    public void shouldReturnNullPointerExceptionIfTheServiceIdIsNull(){
        //Arrange
        serviceId = null;

        //Act
        Executable executable = () -> new ServiceJob(serviceId, type);

        //Assert
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void shouldReturnNullPointerExceptionIfTheTypeIsNull(){
        //Arrange
        type = null;

        //Act
        Executable executable = () -> new ServiceJob(serviceId, type);

        //Assert
        assertThrows(NullPointerException.class, executable);

    }
}