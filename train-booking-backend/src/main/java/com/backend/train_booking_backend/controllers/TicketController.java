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

import com.backend.train_booking_backend.models.Ticket;
import com.backend.train_booking_backend.services.ITicketService;

import jakarta.validation.ValidationException;

@RestController
@Validated
@RequestMapping("/api/ticket")
public class TicketController {
	@Autowired
	private ITicketService ticketService;

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<List<Ticket>> getAllTicket() {
		List<Ticket> tickets = ticketService.getAllTickets();
		if (tickets.isEmpty()) {
			tickets = new ArrayList<>();
		}
		return new ResponseEntity<>(tickets, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Ticket> getTicket(@PathVariable Integer id) {
		Ticket ticket = ticketService.getTicket(id);
		if (ticket == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket) {
		Ticket createdTicket = ticketService.addTicket(ticket);
		return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket, @PathVariable Integer id) {
		Ticket updatedTicket = ticketService.updateTicket(id, ticket);
		if (updatedTicket == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
	}



	@DeleteMapping("/{id}")
	public ResponseEntity<Ticket> deleteTicket(@PathVariable Integer id) {
		if (ticketService.deleteTicket(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
