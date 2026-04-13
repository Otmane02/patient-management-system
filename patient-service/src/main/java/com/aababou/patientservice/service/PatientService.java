package com.aababou.patientservice.service;

import com.aababou.patientservice.dto.PatientRequestDto;
import com.aababou.patientservice.dto.PatientResponseDto;
import com.aababou.patientservice.exception.EmailAlreadyExistException;
import com.aababou.patientservice.exception.PatientNotFoundException;
import com.aababou.patientservice.mapper.PatientMapper;
import com.aababou.patientservice.model.Patient;
import com.aababou.patientservice.repository.PatientRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/*
author otman
    */
@Service
public class PatientService {

    private final PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    public List<PatientResponseDto> getPatients(){
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().map(PatientMapper::toDto).toList();
    }

    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto){
        if (patientRepository.existsByEmail(patientRequestDto.getEmail())) {
            throw new EmailAlreadyExistException("A patient with this address is already exist :"+patientRequestDto.getEmail());
        }
        Patient   newPatient= patientRepository.save(
                PatientMapper.toModel(patientRequestDto));

    return PatientMapper.toDto(newPatient);
    }

    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto){

        Patient patient = patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("Patient Not Found id :"+id));

        if (patientRepository.existsByEmail(patientRequestDto.getEmail())) {
            throw new EmailAlreadyExistException("A patient with this address is already exist :"+patientRequestDto.getEmail());
        }
        patient.setName(patientRequestDto.getName());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
        patientRepository.save(patient);
        return PatientMapper.toDto(patient);

    }

    public PatientResponseDto deletePatient(UUID id){
        Patient patient = patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("Patient Not Found id :"+id));
        patientRepository.delete(patient);
        return PatientMapper.toDto(patient);
    }
}
