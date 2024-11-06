package com.backend.train_booking_backend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.backend.train_booking_backend.models.AppUser;
import com.backend.train_booking_backend.repositories.UserRepository;
import com.backend.train_booking_backend.services.IUserService;

import jakarta.validation.ValidationException;

@RestController
@Validated
@RequestMapping("/api/user")
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

	@GetMapping
	public ResponseEntity<List<AppUser>> getAllUser() {
		List<AppUser> users = userService.getAllUsers();
		if (users.isEmpty()) {
			users = new ArrayList<AppUser>();
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<AppUser> getUser(@PathVariable Integer id) {
		AppUser user = userService.getUser(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<AppUser> addUser(@RequestBody AppUser user) {
		AppUser createdUser = userService.addUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
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

	@DeleteMapping("/{id}")
	public ResponseEntity<AppUser> deleteUser(@PathVariable Integer id) {
		Optional<AppUser> deletedUser = userService.deleteUser(id);
		if (deletedUser.isPresent()) {
			return new ResponseEntity<>(deletedUser.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// @GetMapping("/findByEmail/{email}")
	// public ResponseEntity<AppUser> findUserByEmail(@PathVariable String email) {
	// AppUser user = userService.findByEmail(email);
	// if (user == null) {
	// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// }
	// return new ResponseEntity<>(user, HttpStatus.OK);
	// }

	@GetMapping("/findByEmailAndPassword/{email}/{password}")
	public ResponseEntity<AppUser> findUserByEmailAndPassword(@PathVariable String email,
			@PathVariable String password) {

		AppUser user = userService.findUserByEmailAndPassword(email, password);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
