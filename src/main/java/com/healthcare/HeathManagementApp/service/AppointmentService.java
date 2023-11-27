package com.healthcare.HeathManagementApp.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.healthcare.HeathManagementApp.entity.Appointment;
import com.healthcare.HeathManagementApp.persistence.AppointmentRepository;

public class AppointmentService {
	
    @Autowired
    private AppointmentRepository appointmentRepository;

	public Appointment createAppointment(Appointment appointment) {		
		validateCreateAppointment(appointment);	
		return appointmentRepository.save(appointment);
	}

	private void validateCreateAppointment(Appointment appointment) {
		 if (appointment.getDateTime() != null && appointment.getDateTime().isBefore(LocalDateTime.now())) {
	            throw new IllegalArgumentException("Appointment date-time must be in the future");
	        }
		
	}

	public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
		validateUpdateAppointment(id, updatedAppointment);
		return appointmentRepository.save(updatedAppointment);
	}

	private void validateUpdateAppointment(Long id, Appointment updatedAppointment) {
		// Validate the appointment exists
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with id: " + id));

            // Validate appointment date-time is in the future
            if (updatedAppointment.getDateTime() != null && updatedAppointment.getDateTime().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("Appointment date-time must be in the future");
            }
	}

}
