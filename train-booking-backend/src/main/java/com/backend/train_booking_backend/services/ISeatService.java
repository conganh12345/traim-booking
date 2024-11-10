package com.backend.train_booking_backend.services;

import java.util.List;

import com.backend.train_booking_backend.models.Seat;

public interface ISeatService {
	List<Seat> getAllSeats();

	Seat getSeat(Integer id);

	Seat addSeat(Seat seat);

	Seat updateSeat(Integer id, Seat seat);

	int deleteSeat(Integer id);
}
