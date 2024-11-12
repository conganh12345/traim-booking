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

import com.backend.train_booking_backend.models.Station;
import com.backend.train_booking_backend.services.IStationService;

import jakarta.validation.ValidationException;

@RestController
@Validated
@RequestMapping("/api/station")
public class StationController {
	@Autowired
	private IStationService stationService;

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Station>> getAllStation() {
		List<Station> stations = stationService.getAllStations();
		if (stations.isEmpty()) {
			stations = new ArrayList<>();
		}
		return new ResponseEntity<>(stations, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Station> getStation(@PathVariable Integer id) {
		Station station = stationService.getStation(id);
		if (station == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(station, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Station> addStation(@RequestBody Station station) {
		Station createdStation = stationService.addStation(station);

		return new ResponseEntity<>(createdStation, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Station> updateStation(@RequestBody Station station, @PathVariable Integer id) {
		Station updatedStation = stationService.updateStation(id, station);
		if (updatedStation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedStation, HttpStatus.OK);
	}



	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Station> deleteStation(@PathVariable Integer id) {
		if (stationService.deleteStation(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
