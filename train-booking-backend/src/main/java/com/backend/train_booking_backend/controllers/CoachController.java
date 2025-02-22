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

import com.backend.train_booking_backend.models.Coach;
import com.backend.train_booking_backend.services.ICoachService;

import jakarta.validation.ValidationException;

@RestController
@Validated
@RequestMapping("/api/coach")
public class CoachController {
	@Autowired
	private ICoachService coachService;

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Coach>> getAllCoach() {
		List<Coach> coach = coachService.getAllCoachs();
		if (coach.isEmpty()) {
			coach = new ArrayList<>();
		}
		return new ResponseEntity<>(coach, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Coach> getCoach(@PathVariable Integer id) {
		Coach coach = coachService.getCoach(id);
		if (coach == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(coach, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Coach> addCoach(@RequestBody Coach coach) {
		Coach createdCoach = coachService.addCoach(coach);
		return new ResponseEntity<>(createdCoach, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Coach> updateCoach(@RequestBody Coach coach, @PathVariable Integer id) {
		Coach updatedCoach = coachService.updateCoach(id, coach);
		if (updatedCoach == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedCoach, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteCoach(@PathVariable Integer id) {
		switch (coachService.deleteCoach(id)) {
			case 2:
				return new ResponseEntity<>("Không tìm thấy toa này: " + id, HttpStatus.NOT_FOUND);
			case 1:
				return new ResponseEntity<>("Toa này đã bị xóa", HttpStatus.OK);
			default:
				return new ResponseEntity<>("Không thể xóa toa với ID: " + id, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/list/{trainId}")
	public ResponseEntity<List<Coach>> getCoaches(@PathVariable("trainId") int trainId) {
	    List<Coach> coaches = coachService.getCoachesByTrainId(trainId);
	    if (coaches.isEmpty()) {
	        return ResponseEntity.noContent().build(); 
	    }
	    return ResponseEntity.ok(coaches); 
	}
}
