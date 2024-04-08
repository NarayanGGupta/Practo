package com.practo.practo.serviceImpl;

import com.practo.practo.Service.DoctorService;
import com.practo.practo.entity.Doctor;
import com.practo.practo.entity.Review;
import com.practo.practo.payload.DoctorDto;
import com.practo.practo.repository.DoctorRepository;
import com.practo.practo.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    private ModelMapper modelMapper;

    private ReviewRepository reviewRepository;

    @Override
    public DoctorDto addDoctor(DoctorDto doctorDto) {

        Doctor doctor = mapToEntity(doctorDto);

        Doctor savedDoctor = doctorRepository.save(doctor);

        DoctorDto dto = mapToDto(savedDoctor);

        return dto;
    }

    @Override
    public List<Doctor> searchDoctors(String name, String specializations, String hospital) {
        return doctorRepository.findByNameContainingIgnoreCaseOrSpecializationsContainingIgnoreCaseOrHospitalContainingIgnoreCase(name, specializations, hospital);
    }

    @Override
    public DoctorDto getDoctorAlongWithAllReviews(long doctorId) {
        Optional<Doctor> byId = doctorRepository.findById(doctorId);

        Doctor doctor = byId.orElseThrow(()->new RuntimeException("Doctor not found with Id:"+doctorId));

        List<Review> reviews = reviewRepository.getReviewsByDoctorId(doctorId);

        DoctorDto doctorDto = new DoctorDto();

        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getName());
        doctorDto.setDescription(doctor.getDescription());
        doctorDto.setExperience(doctor.getExperience());
        doctorDto.setSpecializations(doctor.getSpecializations());
        doctorDto.setQualification(doctor.getQualification());
        doctorDto.setHospital(doctor.getHospital());

        double totalRating = 0;

        for(Review review : reviews){
            totalRating = totalRating + review.getRating();
        }
        double averageRating = totalRating/ reviews.size();
        double ratingPercentage = (averageRating/5.0)*100.0;

        doctorDto.setRatingPercentage(ratingPercentage);
        doctorDto.setReviews(reviews);

        return doctorDto;
    }

    Doctor mapToEntity(DoctorDto doctorDto){
        Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
        return doctor;
    }

    DoctorDto mapToDto(Doctor doctor){
        DoctorDto dto = modelMapper.map(doctor, DoctorDto.class);
        return dto;
    }

}
