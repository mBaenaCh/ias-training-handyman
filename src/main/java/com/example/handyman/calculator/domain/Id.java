package com.example.handyman.calculator.domain;

import java.util.Objects;
import java.util.UUID;

public record Id(UUID value) {

    public Id {
        Objects.requireNonNull(value, "The ID must not be null");
    }

    public static Id generateUUID(){
        return new Id(UUID.randomUUID());
    }

    public static Id generateUUIDFromString(String value){
        return new Id(UUID.fromString(value));
    }

    @Override
    public String toString(){
        return this.value.toString();
    }
}
