package com.frontend.train_booking_frontend_customer.services;

import java.util.List;

import com.frontend.train_booking_frontend_customer.models.Seat;

public interface ISeatService {
	List<Seat> getSeatesByCoachId(int coachId);
}
