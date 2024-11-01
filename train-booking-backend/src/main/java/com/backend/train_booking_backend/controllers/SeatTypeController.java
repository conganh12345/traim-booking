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

import com.backend.train_booking_backend.models.Coach;
import com.backend.train_booking_backend.models.SeatType;
import com.backend.train_booking_backend.services.ISeatTypeService;

@RestController
@Validated
@RequestMapping("/api/seatType")
public class SeatTypeController {
	@Autowired
	private ISeatTypeService seatTypeService;

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<List<SeatType>> getAllSeatType() {
		List<SeatType> seatTypes = seatTypeService.getAllSeatTypes();
		if (seatTypes.isEmpty()) {
			seatTypes = new ArrayList<SeatType>();
		}
		return new ResponseEntity<>(seatTypes, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<SeatType> getSeatType(@PathVariable Integer id) {
		SeatType seatType = seatTypeService.getSeatType(id);
		if (seatType == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(seatType, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<SeatType> addSeatType(@RequestBody SeatType seatType) {
		SeatType createdSeatType = seatTypeService.addSeatType(seatType);
		return new ResponseEntity<>(createdSeatType, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SeatType> updateSeatType(@RequestBody SeatType seatType, @PathVariable Integer id) {
		SeatType updatedSeatType = seatTypeService.updateSeatType(id, seatType);
		if (updatedSeatType == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedSeatType, HttpStatus.OK);
	}

	@GetMapping("/{seattypename}")
	public ResponseEntity<SeatType> getSeatTypeBySeatTypename(@PathVariable String seatTypeName) {
		SeatType seatType = seatTypeService.getSeatTypeBySeatTypename(seatTypeName);
		if (seatType == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(seatType, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SeatType> deleteSeatType(@PathVariable Integer id) {
		Optional<SeatType> deletedSeatType = seatTypeService.deleteSeatType(id);
		if (deletedSeatType.isPresent()) {
			return new ResponseEntity<>(deletedSeatType.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
