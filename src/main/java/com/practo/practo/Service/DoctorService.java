package com.practo.practo.Service;
import com.practo.practo.entity.Doctor;
import com.practo.practo.payload.DoctorDto;
import java.util.List;


public interface DoctorService {

    DoctorDto addDoctor(DoctorDto doctorDto);

    List<Doctor> searchDoctors(String name, String specializations, String hospital);

    DoctorDto getDoctorAlongWithAllReviews(long doctorId);
}
