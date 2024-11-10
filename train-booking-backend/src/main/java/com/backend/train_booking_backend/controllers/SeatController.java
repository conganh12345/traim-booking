package com.backend.train_booking_backend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.train_booking_backend.models.Seat;
import com.backend.train_booking_backend.services.ISeatService;

import jakarta.validation.ValidationException;

@RestController
@Validated
@RequestMapping("/api/seat")
public class SeatController {
	@Autowired
	private ISeatService seatService;

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<List<Seat>> getAllSeat() {
		List<Seat> seat = seatService.getAllSeats();
		if (seat.isEmpty()) {
			seat = new ArrayList<>();
		}
		return new ResponseEntity<>(seat, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Seat> getSeat(@PathVariable Integer id) {
		Seat seat = seatService.getSeat(id);
		if (seat == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(seat, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Seat> addSeat(@RequestBody Seat seat) {
		Seat createdSeat = seatService.addSeat(seat);
		return new ResponseEntity<>(createdSeat, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Seat> updateSeat(@RequestBody Seat seat, @PathVariable Integer id) {
		Seat updatedSeat = seatService.updateSeat(id, seat);
		if (updatedSeat == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedSeat, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteSeat(@PathVariable Integer id) {
		switch (seatService.deleteSeat(id)) {
			case 2:
				return new ResponseEntity<>("Không tìm thấy ghế này: " + id, HttpStatus.NOT_FOUND);
			case 1:
				return new ResponseEntity<>("Ghế đã bị xóa", HttpStatus.OK);
			default:
				return new ResponseEntity<>("Không thể xóa ghế với ID: " + id, HttpStatus.BAD_REQUEST);
		}
	}
}
