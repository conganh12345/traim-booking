package com.backend.train_booking_backend.services;

import java.util.List;

import com.backend.train_booking_backend.models.Seat;
import com.backend.train_booking_backend.models.Ticket;

public interface ITicketService {
	List<Ticket> getAllTickets();

	Ticket getTicket(Integer id);

	Ticket addTicket(Ticket ticket);

	Ticket updateTicket(Integer id, Ticket ticket);

	int deleteTicket(Integer id);
	
	List<Ticket> addTickets(List<Ticket> tickets);
	
	List<Integer> getBookedSeatsForSchedule(Integer scheduleId);
	
	List<Ticket> getTicketsByBookingId(int bookingId);
}
