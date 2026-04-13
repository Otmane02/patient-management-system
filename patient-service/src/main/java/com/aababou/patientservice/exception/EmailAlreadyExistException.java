package com.aababou.patientservice.exception;

/*
author otman
    */public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
