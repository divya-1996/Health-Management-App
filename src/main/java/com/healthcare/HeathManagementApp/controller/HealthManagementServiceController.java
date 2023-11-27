package com.healthcare.HeathManagementApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.healthcare.HeathManagementApp.entity.Appointment;
import com.healthcare.HeathManagementApp.service.AppointmentService;

public class HealthManagementServiceController {

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping
	public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
		Appointment createdAppointment = appointmentService.createAppointment(appointment);
		return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id,
			@RequestBody Appointment updatedAppointment) {
		Appointment appointment = appointmentService.updateAppointment(id, updatedAppointment);
		return new ResponseEntity<>(appointment, HttpStatus.OK);
	}
}
