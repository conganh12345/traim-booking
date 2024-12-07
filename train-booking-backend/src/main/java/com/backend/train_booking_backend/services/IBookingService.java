package com.backend.train_booking_backend.services;

import java.util.List;
import java.util.Map;

import com.backend.train_booking_backend.models.Booking;

public interface IBookingService {
	List<Booking> getAllBookings();

	Booking getBooking(Integer id);

	Booking addBooking(Booking booking);

	Booking updateBooking(Integer id, Booking booking);

	int deleteBooking(Integer id);
	
	Booking findByCode(String code);
	
	Booking findById(int bookingId);
	
	List<Booking> findByUserId(int userId);
	
	Map<String, Integer> getBookingStatistics();
	
	Map<String, Long> getRevenueForLast15Days();
}
