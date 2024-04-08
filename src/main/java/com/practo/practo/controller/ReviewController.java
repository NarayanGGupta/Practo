package com.practo.practo.controller;

import com.practo.practo.Service.ReviewService;
import com.practo.practo.entity.Review;
import com.practo.practo.payload.DoctorDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review){
        Review review1 = reviewService.createReview(review);
        return new ResponseEntity<>(review1, HttpStatus.CREATED);
    }
}
