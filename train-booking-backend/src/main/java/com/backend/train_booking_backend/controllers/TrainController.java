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

import com.backend.train_booking_backend.models.Train;
import com.backend.train_booking_backend.services.ITrainService;

import jakarta.validation.ValidationException;

@RestController
@Validated
@RequestMapping("/api/train")
public class TrainController {
	@Autowired
	private ITrainService trainService;

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Train>> getAllTrain() {
		List<Train> trains = trainService.getAllTrains();
		if (trains.isEmpty()) {
			trains = new ArrayList<>();
		}
		return new ResponseEntity<>(trains, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Train> getTrain(@PathVariable Integer id) {
		Train train = trainService.getTrain(id);
		if (train == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(train, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Train> addTrain(@RequestBody Train train) {
		Train createdTrain = trainService.addTrain(train);
		return new ResponseEntity<>(createdTrain, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Train> updateTrain(@RequestBody Train train, @PathVariable Integer id) {
		Train updatedTrain = trainService.updateTrain(id, train);
		if (updatedTrain == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedTrain, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteTrain(@PathVariable Integer id) {
		switch (trainService.deleteTrain(id)) {
			case 2:
				return new ResponseEntity<>("Không tìm thấy tàu này: " + id, HttpStatus.NOT_FOUND);
			case 1:
				return new ResponseEntity<>("Tàu này đã bị xóa", HttpStatus.OK);
			default:
				return new ResponseEntity<>("Không thể xóa tàu với ID: " + id, HttpStatus.BAD_REQUEST);
		}
	}
}
