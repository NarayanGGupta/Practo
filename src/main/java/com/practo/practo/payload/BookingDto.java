package com.practo.practo.payload;

import lombok.Data;

@Data
public class BookingDto {
    private long id;
    private long doctorId;
    private long patientId;
    private String bookingTime;
}
