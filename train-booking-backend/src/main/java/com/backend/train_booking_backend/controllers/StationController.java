package com.backend.train_booking_backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.backend.train_booking_backend.exception.StationValidate;
import com.backend.train_booking_backend.models.Station;
import com.backend.train_booking_backend.services.IStationService;

@RestController
@Validated
@RequestMapping("/api/station")
public class StationController {
	@Autowired
	private IStationService stationService;
	private StationValidate validation = new StationValidate();

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<List<Station>> getAllStation() {
		List<Station> stations = stationService.getAllStations();
		if (stations.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(stations, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Station> getStation(@PathVariable Integer id) {
		Station station = stationService.getStation(id);
		if (station == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(station, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Station> addStation(@RequestBody Station station) {
		validation.validate(station);
		Station createdStation = stationService.addStation(station);

		return new ResponseEntity<>(createdStation, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Station> updateStation(@RequestBody Station station, @PathVariable Integer id) {
		validation.validate(station);
		Station updatedStation = stationService.updateStation(id, station);
		if (updatedStation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedStation, HttpStatus.OK);
	}

	@GetMapping("/{stationname}")
	public ResponseEntity<Station> getStationByStationname(@PathVariable String stationname) {
		Station station = stationService.getStationByStationname(stationname);
		if (station == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(station, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<List<Station>> deleteStation(@RequestBody Integer[] ids) {
		List<Station> deletedStations = stationService.deleteStation(ids);
		if (deletedStations.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(deletedStations, HttpStatus.OK);
	}
}