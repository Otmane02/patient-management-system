package com.aababou.patientservice.exception;

/*
author otman
    */
public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) {
        super(message);
    }
}
