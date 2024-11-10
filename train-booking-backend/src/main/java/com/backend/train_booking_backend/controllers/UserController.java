package com.backend.train_booking_backend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.backend.train_booking_backend.models.AppUser;
import com.backend.train_booking_backend.services.IUserService;

import jakarta.validation.ValidationException;

@RestController
@Validated
@RequestMapping("/api")
public class UserController {
	@Autowired
	private IUserService userService;

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/admin")
	public ResponseEntity<List<AppUser>> getAllUser() {
		List<AppUser> users = userService.getAllUsers();
		if (users.isEmpty()) {
			users = new ArrayList<AppUser>();
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/profile/id/{id}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<AppUser> getUser(@PathVariable Integer id) {
		AppUser user = userService.getUser(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/admin")
	public ResponseEntity<AppUser> addUser(@RequestBody AppUser user) {
		AppUser createdUser = userService.addUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@PutMapping("/admin/{id}")
	public ResponseEntity<AppUser> updateUser(@RequestBody AppUser user, @PathVariable Integer id) {
		AppUser updatedUser = userService.updateUser(id, user);
		if (updatedUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	// @GetMapping("/{username}")
	// public ResponseEntity<AppUser> getUserByUsername(@PathVariable String
	// username) {
	// AppUser user = userService.getUserByUsername(username);
	// if (user == null) {
	// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// }
	// return new ResponseEntity<>(user, HttpStatus.OK);
	// }

	@DeleteMapping("/admin/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		switch (userService.deleteUser(id)) {
			case 2:
				return new ResponseEntity<>("Không tìm thấy người dùng này: " + id, HttpStatus.NOT_FOUND);
			case 1:
				return new ResponseEntity<>("Người dùng này đã bị xóa", HttpStatus.OK);
			default:
				return new ResponseEntity<>("Không thể xóa người dùng với ID: " + id, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/user/findByEmail/{email}")
	public ResponseEntity<AppUser> findUserByEmail(@PathVariable String email) {
		AppUser user = userService.findUserByEmail(email);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/admin/findByEmailAndPassword/{email}/{password}")
	public ResponseEntity<AppUser> findUserByEmailAndPassword(@PathVariable String email,
			@PathVariable String password) {

		AppUser user = userService.findUserByEmailAndPassword(email, password);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
