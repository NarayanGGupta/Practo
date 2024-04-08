package com.practo.practo.payload;

import com.practo.practo.entity.Review;
import lombok.Data;

import java.util.List;

@Data
public class DoctorDto {
    private long id;
    private String name;
    private String description;
    private int experience;
    private String specializations;
    private String qualification;
    private String hospital;

    private List<Review> reviews;

    private double ratingPercentage;

}
