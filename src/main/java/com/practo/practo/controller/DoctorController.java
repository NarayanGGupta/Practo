package com.practo.practo.controller;

import com.practo.practo.Service.DoctorService;
import com.practo.practo.entity.Doctor;
import com.practo.practo.payload.DoctorDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private DoctorService doctorService;

    // http://localhost:8080/api/doctors
    @PostMapping
    public ResponseEntity<DoctorDto> addDoctor(@RequestBody DoctorDto doctorDto) {
        DoctorDto dto = doctorService.addDoctor(doctorDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/doctors/search?specializations=cardiology
    @GetMapping("/search")
    public ResponseEntity<List<Doctor>> searchDoctors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specializations,
            @RequestParam(required = false) String hospital) {
        List<Doctor> doctors = doctorService.searchDoctors(name, specializations, hospital);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    //http://localhost:8080/api/doctors/4
    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDto> getDoctorAlongWithAllReviews(@PathVariable long doctorId){
        DoctorDto doctorAlongWithAllReviews = doctorService.getDoctorAlongWithAllReviews(doctorId);
        return new ResponseEntity<>(doctorAlongWithAllReviews,HttpStatus.OK);
    }
}
