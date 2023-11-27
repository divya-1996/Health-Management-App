package com.healthcare.HeathManagementApp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.HeathManagementApp.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
