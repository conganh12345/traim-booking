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

import com.backend.train_booking_backend.models.Route;
import com.backend.train_booking_backend.models.enums.Province;
import com.backend.train_booking_backend.services.IRouteService;

import jakarta.validation.ValidationException;

@RestController
@Validated
@RequestMapping("/api/route")
public class RouteController {
	@Autowired
	private IRouteService routeService;

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Route>> getAllRoute() {
		List<Route> route = routeService.getAllRoutes();
		if (route.isEmpty()) {
			route = new ArrayList<>();
		}
		return new ResponseEntity<>(route, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Route> getRoute(@PathVariable Integer id) {
		Route route = routeService.getRoute(id);
		if (route == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(route, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Route> addRoute(@RequestBody Route route) {
		Route createdRoute = routeService.addRoute(route);
		return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Route> updateRoute(@RequestBody Route route, @PathVariable Integer id) {
		Route updatedRoute = routeService.updateRoute(id, route);
		if (updatedRoute == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedRoute, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteRoute(@PathVariable Integer id) {
		switch (routeService.deleteRoute(id)) {
			case 2:
				return new ResponseEntity<>("Không tìm thấy tuyến đường này: " + id, HttpStatus.NOT_FOUND);
			case 1:
				return new ResponseEntity<>("Tuyến đường đã bị xóa", HttpStatus.OK);
			default:
				return new ResponseEntity<>("Không thể xóa tuyến đường với ID: " + id, HttpStatus.BAD_REQUEST);
		}
	}

}
