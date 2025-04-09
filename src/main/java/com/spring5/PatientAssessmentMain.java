/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import com.spring5.entity.Assessment;
import com.spring5.entity.Patient;
import com.spring5.entity.Question;
import com.spring5.repository.PatientRepository;
import com.spring5.service.AnswerService;
import com.spring5.service.AssessmentService;
import com.spring5.service.PatientService;
import com.spring5.service.QuestionService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author javaugi
 */
@Service
public class PatientAssessmentMain {
    
    @Autowired
    private PatientRepository patientRepository;
    
    public static void main(String[] args) {
        try{
            PatientAssessmentMain m = new PatientAssessmentMain();
            m.run(args);
        }catch(Exception e) {
        }
    }
    
    public void run(String... args) throws Exception {
        Patient patient1 = PatientService.createPatient("John Doe", LocalDate.of(1980, 5, 15));
        Patient patient2 = PatientService.createPatient("Jane Smith", LocalDate.of(1992, 10, 20));

        Assessment assessment1 = AssessmentService.createAssessment(patient1.getId(), LocalDate.now(), "Completed");
        Assessment assessment2 = AssessmentService.createAssessment(patient2.getId(), LocalDate.now().minusDays(7), "Pending");

        Question question1 = QuestionService.createQuestion("Are you experiencing any pain?");
        Question question2 = QuestionService.createQuestion("Do you have a fever?");

        AnswerService.createAnswer(assessment1.getId(), question1.getId(), "Yes, mild");
        AnswerService.createAnswer(assessment1.getId(), question2.getId(), "No");
        AnswerService.createAnswer(assessment2.getId(), question1.getId(), "No");
    }    
}
