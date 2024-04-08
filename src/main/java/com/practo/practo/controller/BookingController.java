package com.practo.practo.controller;

import com.practo.practo.Service.BookingService;
import com.practo.practo.payload.BookingDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody BookingDto bookingDto){
        bookingService.createBooking(bookingDto);
        return new ResponseEntity<>("Booking Is Confirmed!!", HttpStatus.CREATED);
    }
}
