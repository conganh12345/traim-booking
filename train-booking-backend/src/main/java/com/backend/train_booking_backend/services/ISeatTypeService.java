package com.backend.train_booking_backend.services;

import java.util.List;
import java.util.Optional;

import com.backend.train_booking_backend.models.SeatType;

public interface ISeatTypeService {
	List<SeatType> getAllSeatTypes();

	SeatType getSeatType(Integer id);

	SeatType addSeatType(SeatType seattype);

	SeatType updateSeatType(Integer id, SeatType seattype);


	Optional<SeatType> deleteSeatType(Integer id);
}
