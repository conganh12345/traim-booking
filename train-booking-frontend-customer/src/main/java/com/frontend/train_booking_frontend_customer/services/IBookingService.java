package com.frontend.train_booking_frontend_customer.services;

import com.frontend.train_booking_frontend_customer.models.Booking;

public interface IBookingService {
	Booking findByCode(String code);
	Booking insertBooking(Booking booking);
}
