package com.example.bookings.controller;

import com.example.bookings.model.Booking;
import com.example.bookings.model.BookingAvailableResponse;
import com.example.bookings.model.BookingResponse;
import com.example.bookings.service.BookingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping
    public Mono<BookingResponse> saveBooking(@Valid @RequestBody Booking booking)  {
        return bookingService.saveBooking(booking);
    }

    @PostMapping("/checkAvailable")
    public Mono<BookingAvailableResponse> checkAvailable(@Valid @RequestBody Booking booking)  {
        return bookingService.checkAvailable();
    }



}
