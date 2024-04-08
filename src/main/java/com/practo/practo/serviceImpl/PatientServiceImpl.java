package com.practo.practo.serviceImpl;

import com.practo.practo.Service.PatientService;
import com.practo.practo.entity.Patient;
import com.practo.practo.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    @Override
    public Patient createPatient(Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
        return patient;
    }
}
