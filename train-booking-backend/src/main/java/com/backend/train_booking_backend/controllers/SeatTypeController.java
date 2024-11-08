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

import com.backend.train_booking_backend.models.SeatType;
import com.backend.train_booking_backend.services.ISeatTypeService;

import jakarta.validation.ValidationException;

@RestController
@Validated
@RequestMapping("/api/seattype")
public class SeatTypeController {
	@Autowired
	private ISeatTypeService seattypeService;

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<List<SeatType>> getAllSeatType() {
		List<SeatType> seattype = seattypeService.getAllSeatTypes();
		if (seattype.isEmpty()) {
			seattype = new ArrayList<>();
		}
		return new ResponseEntity<>(seattype, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<SeatType> getSeatType(@PathVariable Integer id) {
		SeatType seattype = seattypeService.getSeatType(id);
		if (seattype == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(seattype, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<SeatType> addSeatType(@RequestBody SeatType seattype) {
		SeatType createdSeatType = seattypeService.addSeatType(seattype);
		return new ResponseEntity<>(createdSeatType, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SeatType> updateSeatType(@RequestBody SeatType seattype, @PathVariable Integer id) {
		SeatType updatedSeatType = seattypeService.updateSeatType(id, seattype);
		if (updatedSeatType == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedSeatType, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SeatType> deleteSeatType(@PathVariable Integer id) {
	    if (seattypeService.deleteSeatType(id)) {
	        return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
