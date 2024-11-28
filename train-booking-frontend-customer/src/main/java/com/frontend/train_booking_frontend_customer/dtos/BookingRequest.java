package com.frontend.train_booking_frontend_customer.dtos;

import java.util.List;

import com.frontend.train_booking_frontend_customer.models.Booking;
import com.frontend.train_booking_frontend_customer.models.Ticket;

public class BookingRequest {
    private Booking booking;
    private List<Ticket> tickets;

    // Getters and Setters
    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}

