package com.healthcare.HeathManagementApp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.HeathManagementApp.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
