package com.frontend.train_booking_frontend_customer.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.frontend.train_booking_frontend_customer.models.Seat;
import com.frontend.train_booking_frontend_customer.models.Ticket;

@Service
public interface ITicketService {
	boolean addTickets(List<Ticket> tickets);
	
	List<Integer> getBookedSeats(Integer scheduleId);
	
	List<Ticket> getTicketsByBookingId(int bookingId);
}
