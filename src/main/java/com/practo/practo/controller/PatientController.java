package com.practo.practo.controller;

import com.practo.practo.Service.PatientService;
import com.practo.practo.entity.Patient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private PatientService patientService;

   // http://localhost:8080/api/patients
    @PostMapping
    public ResponseEntity<Patient> savePatients(@RequestBody Patient patient){
        Patient patient1 = patientService.createPatient(patient);
        return new ResponseEntity<>(patient1, HttpStatus.CREATED);
    }
}
