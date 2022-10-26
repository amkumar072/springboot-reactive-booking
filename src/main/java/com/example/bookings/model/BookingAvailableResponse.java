package com.example.bookings.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class BookingAvailableResponse {

    public boolean available;

}
