package com.backend.train_booking_backend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
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
import com.backend.train_booking_backend.models.Booking;
import com.backend.train_booking_backend.services.IBookingService;

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
	public ResponseEntity<List<Booking>> getAllBooking() {
		List<Booking> bookings = bookingService.getAllBookings();
		if (bookings.isEmpty()) {
			bookings = new ArrayList<Booking>();
		}
		return new ResponseEntity<>(bookings, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Booking> getBooking(@PathVariable Integer id) {
		Booking booking = bookingService.getBooking(id);
		if (booking == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
		Booking createdBooking = bookingService.addBooking(booking);
		return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking, @PathVariable Integer id) {
		Booking updatedBooking = bookingService.updateBooking(id, booking);
		if (updatedBooking == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
	}

	@GetMapping("/{fullname}")
	public ResponseEntity<Booking> getBookingByFullName(@PathVariable String fullname) {
		Booking booking = bookingService.getBookingByFullName(fullname);
		if (booking == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Booking> deleteBooking(@PathVariable Integer id) {
	    if (bookingService.deleteBooking(id)) {
	        return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
