package com.healthcare.HeathManagementApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.HeathManagementApp.entity.Appointment;
import com.healthcare.HeathManagementApp.service.AppointmentService;
import com.healthcare.HeathManagementApp.service.PatientService;

@RestController
@RequestMapping("/health/api")
public class HealthManagementServiceController {

	@Autowired
	private AppointmentService appointmentService;
	
    @Autowired
    private PatientService patientService;

	@PostMapping("/appointment")
	public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
		Appointment createdAppointment = appointmentService.createAppointment(appointment);
		return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
	}

	@PutMapping("/appointment/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id,
			@RequestBody Appointment updatedAppointment) {
		Appointment appointment = appointmentService.updateAppointment(id, updatedAppointment);
		return new ResponseEntity<>(appointment, HttpStatus.OK);
	}
	
    @DeleteMapping("/patient/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        try {
            patientService.deletePatient(id);
            return new ResponseEntity<>("Patient and associated appointments deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting patient: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
