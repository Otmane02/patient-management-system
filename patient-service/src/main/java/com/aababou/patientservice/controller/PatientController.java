package com.aababou.patientservice.controller;

import com.aababou.patientservice.dto.PatientRequestDto;
import com.aababou.patientservice.dto.PatientResponseDto;
import com.aababou.patientservice.dto.validators.CreatePatientValidationGroup;
import com.aababou.patientservice.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/*
author otman
    */
@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDto patientRequestDto) {
        PatientResponseDto responseDto = patientService.createPatient(patientRequestDto);
        return ResponseEntity.ok().body(responseDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDto patientRequestDto) {
        PatientResponseDto responseDto = patientService.updatePatient(id, patientRequestDto);
        return ResponseEntity.ok().body(responseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientResponseDto> deletePatient(@PathVariable UUID id) {
        return ResponseEntity.ok().body(patientService.deletePatient(id));
    }
}
