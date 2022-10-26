package com.example.bookings.model;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.*;


@Table("bookings")
@Data
@ToString
public class Booking {

    @PrimaryKey
    @Column(value = "booking_ref")
    private String bookingRef;

    @NotNull(message = "Container Type should not be null")
    @Column(value = "container_type")
    private ContainerType containerType;

    @NotNull(message = "Container Size should not be null")
    @Column(value = "container_size")
    private int containerSize;

    @NotNull(message = "Origin should not be null")
    @Size(min = 5, max = 20, message = "Origin must be between 5 and 20 characters")
    @Column(value = "origin")
    private String origin;

    @NotNull(message = "Destination should not be null")
    @Size(min = 5, max = 20, message = "Destination must be between 5 and 20 characters")
    @Column(value = "destination")
    private String destination;

    @NotNull(message = "Quantity should not be null")
    @Min(value = 1, message = "Quantity should not be less than 1")
    @Max(value = 100, message = "Quantity should not be greater than 100")
    @Column(value = "quantity")
    private int quantity;

    @Column(value = "timestamp")
    private String timestamp;


    public int getContainerSize() {
        return containerSize;
    }

    public void setContainerSize(int containerSize) {
        if (containerSize == 20 || containerSize == 40) {
            this.containerSize = containerSize;
        } else {
            throw new RuntimeException("Container Size should either 20 or 40");
        }
    }
}


