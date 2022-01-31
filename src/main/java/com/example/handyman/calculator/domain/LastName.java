package com.example.handyman.calculator.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class LastName {

    private static final Pattern pattern = Pattern.compile("^[a-zA-Z\\s:]{0,40}$");
    private final String value;

    public LastName(String value){
        Objects.requireNonNull(value, "Technician last name can not be null");

        String trimmedValue = value.trim();

        if(trimmedValue.length() <= 0 || trimmedValue.length() > 40){
            throw new IllegalArgumentException("Technician last name can not be empty or have more than 150 characters");
        }

        Boolean isValid = pattern.matcher(trimmedValue).matches();

        if(!isValid){
            throw new IllegalArgumentException("Technician last name can not have special characters or numbers");
        }

        this.value = trimmedValue;
    }
}
