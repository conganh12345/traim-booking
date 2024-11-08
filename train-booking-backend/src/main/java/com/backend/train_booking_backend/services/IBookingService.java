package com.backend.train_booking_backend.services;

import java.util.List;

import com.backend.train_booking_backend.models.Booking;

public interface IBookingService {
	List<Booking> getAllBookings();

	Booking getBooking(Integer id);

	Booking addBooking(Booking booking);

	Booking updateBooking(Integer id, Booking booking);

	boolean deleteBooking(Integer id);
}
