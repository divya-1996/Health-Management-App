package com.healthcare.HeathManagementApp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.HeathManagementApp.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
