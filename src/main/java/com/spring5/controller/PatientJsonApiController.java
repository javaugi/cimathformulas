/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.controller;

import com.spring5.entity.Patient;
import com.spring5.repository.PatientRepository;
import com.spring5.validator.PatientJsonSchemaValidator;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author javaugi
 */
@RestController
@RequestMapping("/json/patients")
public class PatientJsonApiController {

    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private PatientJsonSchemaValidator jsonValidator;

          
    @PostConstruct
    public void init() {
    }    
    
    @PostMapping("/validate")
    public ResponseEntity<?> validatePatientJson(@RequestBody String patientJson) {
        try {
            jsonValidator.validatePatientJson(patientJson);
            return ResponseEntity.ok().body("JSON is valid");
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }    
    
    @PostMapping
    public ResponseEntity<?> createPatient(@Valid @RequestBody Patient patient, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(createErrorResponse(result));
        }
        
        Patient savedPatient = patientRepository.save(patient);
        return ResponseEntity.ok(savedPatient);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody Patient patient,
            BindingResult result) {
        
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(createErrorResponse(result));
        }
        
        if (!patientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        patient.setId(id);
        Patient updatedPatient = patientRepository.save(patient);
        return ResponseEntity.ok(updatedPatient);
    }
    
    private Map<String, String> createErrorResponse(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }
}

