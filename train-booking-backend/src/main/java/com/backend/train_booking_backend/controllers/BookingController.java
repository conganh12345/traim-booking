package com.backend.train_booking_backend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.backend.train_booking_backend.models.Booking;
import com.backend.train_booking_backend.services.IBookingService;

import jakarta.validation.ValidationException;

@RestController
@Validated
@RequestMapping("/api/booking")
public class BookingController {
	@Autowired
	private IBookingService bookingService;

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Booking>> getAllBooking() {
		List<Booking> booking = bookingService.getAllBookings();
		if (booking.isEmpty()) {
			booking = new ArrayList<>();
		}
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Booking> getBooking(@PathVariable Integer id) {
		Booking booking = bookingService.getBooking(id);
		if (booking == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") 
	public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
		Booking createdBooking = bookingService.addBooking(booking);
		return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") 
	public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking, @PathVariable Integer id) {
		Booking updatedBooking = bookingService.updateBooking(id, booking);
		if (updatedBooking == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") 
	public ResponseEntity<String> deleteBooking(@PathVariable Integer id) {
		switch (bookingService.deleteBooking(id)) {
			case 2:
				return new ResponseEntity<>("Không tìm thấy đặt vé này: " + id, HttpStatus.NOT_FOUND);
			case 1:
				return new ResponseEntity<>("Đặt vé đã bị xóa", HttpStatus.OK);
			default:
				return new ResponseEntity<>("Không thể xóa đặt vé với ID: " + id, HttpStatus.BAD_REQUEST);
		}
	}
}
