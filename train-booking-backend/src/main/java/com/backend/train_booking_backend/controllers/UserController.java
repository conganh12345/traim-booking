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
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.backend.train_booking_backend.models.Train;
import com.backend.train_booking_backend.models.enums.ERole;
import com.backend.train_booking_backend.services.IUserService;

import jakarta.validation.ValidationException;

@RestController
@Validated
@RequestMapping("/api")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<AppUser>> getAllUser() {
		List<AppUser> users = userService.getAllUsers();
		if (users.isEmpty()) {
			users = new ArrayList<AppUser>();
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/profile/id/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<AppUser> getUser(@PathVariable Integer id) {
		AppUser user = userService.getUser(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/admin/id/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<AppUser> getUserById(@PathVariable Integer id) {
		AppUser user = userService.getUser(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/admin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<AppUser> addUser(@RequestBody AppUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		AppUser createdUser = userService.addUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@PutMapping("/admin/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<AppUser> updateAdmin(@RequestBody AppUser user, @PathVariable Integer id) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		AppUser updatedUser = userService.updateUser(id, user);
		if (updatedUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<AppUser> updateUser(@RequestBody AppUser user, @PathVariable Integer id) {
	    if (user.getPassword() != null && !user.getPassword().isEmpty()) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	    } else {
	        AppUser existingUser = userService.getUser(id);
	        if (existingUser != null) {
	            user.setPassword(existingUser.getPassword());  
	        }
	    }
	    user.setRole(ERole.USER);
	    
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
	@PreAuthorize("hasAuthority('ADMIN')")
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
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<AppUser> findUserByEmail(@PathVariable String email) {
		AppUser user = userService.findUserByEmail(email);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/admin/findByEmailAndPassword/{email}/{password}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<AppUser> findUserByEmailAndPassword(@PathVariable String email,
			@PathVariable String password) {

		AppUser user = userService.findUserByEmailAndPassword(email, password);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
