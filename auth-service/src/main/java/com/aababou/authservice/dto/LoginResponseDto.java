package com.aababou.authservice.dto;

/*
author otman
    */

public class LoginResponseDto {

    private final String token;

    public LoginResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
