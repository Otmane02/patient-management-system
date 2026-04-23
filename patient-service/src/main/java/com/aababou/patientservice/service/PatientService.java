package com.aababou.patientservice.service;

import com.aababou.patientservice.dto.PatientRequestDto;
import com.aababou.patientservice.dto.PatientResponseDto;
import com.aababou.patientservice.exception.EmailAlreadyExistException;
import com.aababou.patientservice.exception.PatientNotFoundException;
import com.aababou.patientservice.grpc.BillingServiceGrpcClient;
import com.aababou.patientservice.kafka.KafkaProducer;
import com.aababou.patientservice.mapper.PatientMapper;
import com.aababou.patientservice.model.Patient;
import com.aababou.patientservice.repository.PatientRepository;
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
    private final BillingServiceGrpcClient billingServiceGrpcClient;
    private final KafkaProducer kafkaProducer;

    public PatientService(PatientRepository patientRepository, BillingServiceGrpcClient billingServiceGrpcClient, KafkaProducer kafkaProducer) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
        this.kafkaProducer = kafkaProducer;
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

        billingServiceGrpcClient.createBillingAccount(newPatient.getId().toString(),newPatient.getName(),newPatient.getEmail());

        kafkaProducer.sendEvent(newPatient);

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

    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }
}
