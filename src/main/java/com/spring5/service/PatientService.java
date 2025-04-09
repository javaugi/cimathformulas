/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.service;

import com.spring5.entity.Assessment;
import com.spring5.entity.Patient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author javaugi
 */
@org.springframework.stereotype.Service
public class PatientService {
    private final Map<Long, Patient> patients = new HashMap<>();
    private long nextId = 1;

    public Patient save(Patient patient) {
        //Patient newPatient = new Patient(nextId++, patient.getName(), patient.getDob(), patient.getAppointments());
        Patient newPatient = new Patient(nextId++, patient.getName(), patient.getDob());
        patients.put(newPatient.getId(), newPatient);
        return newPatient;
    }
    
    public static Patient createPatient(String name, LocalDate dob) {
        return Patient.builder()
                .name(name)
                .dob(dob)
                .build();
    }    

    public Patient findById(Long id) {
        return patients.get(id);
    }

    public List<Patient> findAll() {
        return new ArrayList<>(patients.values());
    }

    public Patient update(Long id, Patient updatedPatient) {
        if (!patients.containsKey(id)) {
            return null;
        }
        //patients.put(id, new Patient(id, updatedPatient.getName(), updatedPatient.getDob(), updatedPatient.getAppointments()));
        patients.put(id, new Patient(id, updatedPatient.getName(), updatedPatient.getDob()));
        return patients.get(id);
    }

    public List<Assessment> findAssessmentsByPatientId(Long patientId, AssessmentService assessmentService) {
        return assessmentService.findAll().stream()
                .filter(assessment -> assessment.getPatientId().equals(patientId))
                .collect(Collectors.toList());
    }
}