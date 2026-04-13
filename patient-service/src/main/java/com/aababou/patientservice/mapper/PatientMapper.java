package com.aababou.patientservice.mapper;

import com.aababou.patientservice.dto.PatientRequestDto;
import com.aababou.patientservice.dto.PatientResponseDto;
import com.aababou.patientservice.model.Patient;

import java.time.LocalDate;

/*
author otman
    */
public class PatientMapper {
    public static PatientResponseDto toDto(Patient patient) {
        PatientResponseDto patientDto = new PatientResponseDto();

        patientDto.setId(patient.getId().toString());
        patientDto.setName(patient.getName());
        patientDto.setEmail(patient.getEmail());
        patientDto.setAddress(patient.getAddress());
        patientDto.setDateOfBirth(patient.getDateOfBirth().toString());

        return patientDto;

    }

    public static Patient toModel(PatientRequestDto patientDto) {
        Patient patient = new Patient();

        patient.setName(patientDto.getName());
        patient.setEmail(patientDto.getEmail());
        patient.setAddress(patientDto.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientDto.getDateOfBirth()));
        return patient;
    }
}
