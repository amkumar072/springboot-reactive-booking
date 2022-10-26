package com.example.bookings.service;

import com.example.bookings.constants.ApplicationConstant;
import com.example.bookings.model.Booking;
import com.example.bookings.model.BookingAvailableResponse;
import com.example.bookings.model.BookingResponse;
import com.example.bookings.repository.BookingRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Log4j2
@Service
public class BookingService {

    public static final String BOOKING_REF_PREFIX = "957";
    public static final long INITIAL_VALUE = 100001;

    @Autowired
    BookingRepository bookingRepository;

    public Mono<BookingAvailableResponse> checkAvailable() {
        log.info("entered :: checkAvailable");
        HashMap<String, Integer> stringIntegerHashMap = callService();
        BookingAvailableResponse bookingAvailableResponse = new BookingAvailableResponse();

        Integer availableSpace = stringIntegerHashMap.get("availableSpace");
        if (availableSpace > 0) {
            bookingAvailableResponse.setAvailable(true);
        } else {
            bookingAvailableResponse.setAvailable(false);
        }
        return Mono.just(bookingAvailableResponse);
    }

    public HashMap<String, Integer> callService(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("availableSpace", 1);
        return map;
    }

    public Mono<BookingResponse> saveBooking(Booking booking) {
        log.info("entered :: saveBooking");

        return bookingRepository.count()
                .doOnNext(s -> {
                    long count = INITIAL_VALUE + s;
                    String refno = BOOKING_REF_PREFIX + count;
                    booking.setBookingRef(refno);
                }).flatMap(res -> {
                    return bookingRepository.save(booking);
                }).flatMap(s -> {
                    BookingResponse bookingResponse = new BookingResponse();
                    bookingResponse.setBookingRef(s.getBookingRef());
                    return Mono.just(bookingResponse);
                }).doOnError(err -> {
                    throw new RuntimeException(ApplicationConstant.ERROR_MSG);
                });

    }
}
