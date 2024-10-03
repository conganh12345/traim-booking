package com.backend.train_booking_backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.backend.train_booking_backend.exception.CoachValidate;
import com.backend.train_booking_backend.models.Coach;
import com.backend.train_booking_backend.services.ICoachService;

@RestController
@Validated
@RequestMapping("/api/coach")
public class CoachController {
	@Autowired
	private ICoachService coachService;
	private CoachValidate validation = new CoachValidate();

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<List<Coach>> getAllCoach() {
		List<Coach> coach = coachService.getAllCoachs();
		if (coach.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(coach, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Coach> getCoach(@PathVariable Integer id) {
		Coach coach = coachService.getCoach(id);
		if (coach == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(coach, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Coach> addCoach(@RequestBody Coach coach) {
		validation.validate(coach);
		Coach createdCoach = coachService.addCoach(coach);
		return new ResponseEntity<>(createdCoach, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Coach> updateCoach(@RequestBody Coach coach, @PathVariable Integer id) {
		validation.validate(coach);
		Coach updatedCoach = coachService.updateCoach(id, coach);
		if (updatedCoach == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedCoach, HttpStatus.OK);
	}

	@GetMapping("/{coachname}")
	public ResponseEntity<Coach> getCoachByCoachName(@PathVariable String coachname) {
		Coach coach = coachService.getCoachByCoachName(coachname);
		if (coach == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(coach, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Coach> deleteCoach(@PathVariable Integer id) {
	    Optional<Coach> deletedCoach = coachService.deleteCoach(id); 
	    if (deletedCoach.isPresent()) {
	        return new ResponseEntity<>(deletedCoach.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
