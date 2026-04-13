package com.aababou.patientservice.dto;

import com.aababou.patientservice.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
author otman
    */
public class PatientRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max = 40 , message = "name can't exceed 40 caracteres ")
    private String name;
    @NotBlank(message = "email is required")
    @Email(message = "email should be valid")
    private String email;

    @NotBlank(message ="Address is required")
    private String address;

    @NotBlank(groups = CreatePatientValidationGroup.class, message ="Date of birth is required")
    private String dateOfBirth;


    public @NotBlank(message = "Name is required") @Size(max = 40, message = "name can't exceed 40 caracteres ") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") @Size(max = 40, message = "name can't exceed 40 caracteres ") String name) {
        this.name = name;
    }

    public @NotBlank(message = "email is required") @Email(message = "email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "email is required") @Email(message = "email should be valid") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Address is required") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Address is required") String address) {
        this.address = address;
    }

    public  String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth() {
        this.dateOfBirth = dateOfBirth;
    }



}
