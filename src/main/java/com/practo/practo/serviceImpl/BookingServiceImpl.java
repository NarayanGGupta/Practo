package com.practo.practo.serviceImpl;

import com.practo.practo.Service.BookingService;

import com.practo.practo.entity.Booking;
import com.practo.practo.payload.BookingDto;
import com.practo.practo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private final BookingRepository bookingRepository;

    private final List<String> availableSlotsTime = new ArrayList<>();

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        availableSlotsTime.add("10:30");
        availableSlotsTime.add("12:30");
        availableSlotsTime.add("15:30");
        availableSlotsTime.add("17:15");
    }

    @Override
    public BookingDto createBooking(BookingDto bookingDto) {

        Booking booking = new Booking();

        if(availableSlotsTime.contains(bookingDto.getBookingTime())){
            booking.setBookingTime(bookingDto.getBookingTime());
            availableSlotsTime.remove(bookingDto.getBookingTime());
        }
        else
        {
            throw new RuntimeException("Selected Slots Not Available!!");
        }
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(()->{
            availableSlotsTime.clear();
            availableSlotsTime.add("10:30");
            availableSlotsTime.add("12:30");
            availableSlotsTime.add("15:30");
            availableSlotsTime.add("17:15");

        },24, TimeUnit.HOURS);

        if(booking.getBookingTime()!=null){
            booking.setDoctorId(bookingDto.getDoctorId());
            booking.setPatientId(bookingDto.getPatientId());
            bookingRepository.save(booking);
        }
        else {
            throw new RuntimeException("Slot is not booked!!");
        }
        return bookingDto;
    }
}
