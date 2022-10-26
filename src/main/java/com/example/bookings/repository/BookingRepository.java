package com.example.bookings.repository;

import com.example.bookings.model.Booking;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends ReactiveCassandraRepository<Booking, String> {
}
