package com.practo.practo.repository;

import com.practo.practo.entity.Doctor;
import com.practo.practo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByNameContainingIgnoreCaseOrSpecializationsContainingIgnoreCaseOrHospitalContainingIgnoreCase(String name, String specializations, String hospital);

}
