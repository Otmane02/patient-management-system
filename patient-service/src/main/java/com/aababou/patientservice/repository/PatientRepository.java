package com.aababou.patientservice.repository;

import com.aababou.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/*
author otman
    */
@Repository
public interface PatientRepository  extends JpaRepository<Patient, UUID> {
    boolean existsByEmail(String email);
}
