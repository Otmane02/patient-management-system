package com.aababou.authservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
author otman
    */
public class LoginRequestDto {

    @NotBlank(message = "Message is required")
    @Email(message = "Email should be a valid Email Address ")
    @Column(nullable = false, unique = true)
    private String email;

   @NotBlank(message = "Password is Required")
   @Size(min = 6,message = "Password must be at least  8 characters long ")
    private String password;

    public @NotBlank(message = "Message is required") @Email(message = "Email should be a valid Email Address ") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Message is required") @Email(message = "Email should be a valid Email Address ") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is Required") @Size(min = 6, message = "Password must be at least  8 characters long ") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is Required") @Size(min = 6, message = "Password must be at least  8 characters long ") String password) {
        this.password = password;
    }
}
