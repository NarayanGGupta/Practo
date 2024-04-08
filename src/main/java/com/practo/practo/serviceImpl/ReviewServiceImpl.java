package com.practo.practo.serviceImpl;

import com.practo.practo.Service.ReviewService;
import com.practo.practo.entity.Doctor;
import com.practo.practo.entity.Patient;
import com.practo.practo.entity.Review;
import com.practo.practo.repository.DoctorRepository;
import com.practo.practo.repository.PatientRepository;
import com.practo.practo.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private DoctorRepository doctorRepository;

    private PatientRepository patientRepository;

    private ReviewRepository reviewRepository;

    @Override
    public Review createReview(Review review) {

        Optional<Doctor> byId = doctorRepository.findById(review.getDoctorId());
        Doctor doctor = byId.orElseThrow(() -> new RuntimeException("Doctor not found with Id:" + review.getDoctorId()));

        Optional<Patient> byId1 = patientRepository.findById(review.getPatientId());
        Patient patient = byId1.orElseThrow(() -> new RuntimeException("Patient not found with Id :" + review.getPatientId()));

        if (doctor != null || patient != null) {
            Review savedReview = reviewRepository.save(review);
            return savedReview;
        } else {
            throw new RuntimeException("No Doctor Or Patient found");
        }
    }
}

