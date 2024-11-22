package com.backend.train_booking_backend.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.train_booking_backend.models.Schedule;
import com.backend.train_booking_backend.models.enums.Province;
import com.backend.train_booking_backend.services.IScheduleService;

import jakarta.validation.ValidationException;

@RestController
@Validated
@RequestMapping("/api/schedule")
public class ScheduleController {
	@Autowired
	private IScheduleService scheduleService;

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Schedule>> getAllSchedule() {
		List<Schedule> schedule = scheduleService.getAllSchedules();
		if (schedule.isEmpty()) {
			schedule = new ArrayList<>();
		}
		return new ResponseEntity<>(schedule, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Schedule> getSchedule(@PathVariable Integer id) {
		Schedule schedule = scheduleService.getSchedule(id);
		if (schedule == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(schedule, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Schedule> addSchedule(@RequestBody Schedule schedule) {
		Schedule createdSchedule = scheduleService.addSchedule(schedule);
		return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Schedule> updateSchedule(@RequestBody Schedule schedule, @PathVariable Integer id) {
		Schedule updatedSchedule = scheduleService.updateSchedule(id, schedule);
		if (updatedSchedule == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteSchedule(@PathVariable Integer id) {
		switch (scheduleService.deleteSchedule(id)) {
			case 2:
				return new ResponseEntity<>("Không tìm thấy chuyến đi này: " + id, HttpStatus.NOT_FOUND);
			case 1:
				return new ResponseEntity<>("Chuyến đi này đã bị xóa", HttpStatus.OK);
			default:
				return new ResponseEntity<>("Không thể xóa chuyến đi với ID: " + id, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search")
    public List<Schedule> getSchedules(
            @RequestParam Province departureLocation,
            @RequestParam Province destinationLocation,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate) {
        return scheduleService.getSchedules(departureLocation, destinationLocation, departureDate);
    }
}
