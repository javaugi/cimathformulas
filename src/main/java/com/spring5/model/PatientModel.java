/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.model;


import com.spring5.entity.Patient;
import java.time.LocalDate;
import org.springframework.hateoas.RepresentationModel;

/**
 *
 * @author javaugi
 */
public class PatientModel extends RepresentationModel<PatientModel> {
    private final Patient patient;

    public PatientModel(Patient patient) {
        this.patient = patient;
    }

    public Long getId() {
        return patient.getId();
    }

    public String getName() {
        return patient.getName();
    }

    public LocalDate getDob() {
        return patient.getDob();
    }
}
