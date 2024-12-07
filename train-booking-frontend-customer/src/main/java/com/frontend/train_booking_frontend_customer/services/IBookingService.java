package com.frontend.train_booking_frontend_customer.services;

import java.util.List;

import com.frontend.train_booking_frontend_customer.models.Booking;

public interface IBookingService {
	Booking findByCode(String code);
	Booking insertBooking(Booking booking);
	boolean updateBooking(Booking booking); 
	public String getVNPayByBookingId(int bookingId);
	Booking processPayment(int bookingId);
	List<Booking> getBookingsByUser(int userId);
}
