package com.healthcare.HeathManagementApp.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.HeathManagementApp.Enum.AppointmentStatus;
import com.healthcare.HeathManagementApp.entity.Appointment;
import com.healthcare.HeathManagementApp.entity.Patient;
import com.healthcare.HeathManagementApp.persistence.AppointmentRepository;
import com.healthcare.HeathManagementApp.persistence.PatientRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private PatientRepository patientRepository;

	public Appointment createAppointment(Appointment appointment) {
		validateCreateAppointment(appointment);
		Patient patient = appointment.getPatient();
		if (patient != null && patient.getAppointments() != null) {
			boolean flag = patient.getAppointments().stream()
					.anyMatch(a -> a.getStatus() == AppointmentStatus.COMPLETED);
			if (flag) {
				patient.setNewPatient(false);
				patientRepository.save(patient);
			}
		}
		return appointmentRepository.save(appointment);
	}

	private void validateCreateAppointment(Appointment appointment) {
		if (appointment.getDateTime() != null && appointment.getDateTime().isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("Appointment date-time must be in the future");
		}

	}

	public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
		validateUpdateAppointment(id, updatedAppointment);

		Appointment existingAppointment = appointmentRepository.findById(id).orElse(null);

		if (existingAppointment == null) {
			throw new IllegalArgumentException("Appointment does not exist");
		}
		Patient patient = existingAppointment.getPatient();
		if (patient != null && patient.getAppointments() != null) {
			boolean flag = patient.getAppointments().stream()
					.anyMatch(a -> a.getStatus() == AppointmentStatus.COMPLETED);
			if (flag) {
				patient.setNewPatient(false);
				patientRepository.save(patient);
			}
		}
		existingAppointment.setDateTime(updatedAppointment.getDateTime());
		existingAppointment.setStatus(updatedAppointment.getStatus());

		return appointmentRepository.save(existingAppointment);
	}

	private void validateUpdateAppointment(Long id, Appointment updatedAppointment) {
		// Validate the appointment exists
		Appointment existingAppointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Appointment not found with id: " + id));

		// Validate appointment date-time is in the future
		if (updatedAppointment.getDateTime() != null
				&& updatedAppointment.getDateTime().isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("Appointment date-time must be in the future");
		}
	}

}
