package com.backend.train_booking_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.train_booking_backend.dtos.LoginRequest;
import com.backend.train_booking_backend.models.AppUser;
import com.backend.train_booking_backend.models.enums.ERole;
import com.backend.train_booking_backend.security.JwtUtils;
import com.backend.train_booking_backend.services.IUserService;
import com.backend.train_booking_backend.services.impl.UserDetailsServiceImplement;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private JwtUtils jwtUtil;

	@Autowired
	private UserDetailsServiceImplement userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IUserService userService; // Inject UserRepository

	public static String token;

	@PostMapping("/generateToken")
	public ResponseEntity<String> generateToken(@RequestBody LoginRequest loginRequest) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
		if (userDetails != null && passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
			AppUser user = userService.findUserByEmail(loginRequest.getEmail());

			// Tạo token chứa thông tin từ AppUser
			token = jwtUtil.generateToken(user);

			// Lưu token mới vào lastToken
			user.setLastToken(token);
			userService.save(user);

			return ResponseEntity.ok(token);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
		AppUser user = userService.findUserByEmail(loginRequest.getEmail());

		if (user != null && userDetails != null
				&& passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
			// Generate token and store it
			String token = jwtUtil.generateToken(user);
			user.setLastToken(token);
			userService.save(user);

			if (token == null || !jwtUtil.isTokenValid(token)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}

			Claims claims = jwtUtil.extractAllClaims(token);

			AppUser userLogin = new AppUser();
			if (claims != null) {
				userLogin.setEmail(claims.get("email", String.class));
				userLogin.setFullName(claims.get("fullName", String.class));
				userLogin.setPhoneNumber(claims.get("phoneNumber", String.class));
				userLogin.setAddress(claims.get("address", String.class));
				userLogin.setIdentifyCard(claims.get("identifyCard", String.class));
				String roleString = claims.get("role", String.class);
				if (roleString != null) {
					userLogin.setRole(ERole.valueOf(roleString));
				}
			}

			return ResponseEntity.ok(userLogin);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody AppUser user) {
		// Check if the username already exists
		if (userService.findUserByEmail(user.getEmail()) != null) {
			return ResponseEntity.status(400).body("Email already exists");
		}

		// Encode the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(ERole.USER);

		// Save the user to the database
		userService.save(user);

		return ResponseEntity.ok("User registered successfully");
	}

	@GetMapping("/findByEmail/{email}")
	public ResponseEntity<AppUser> findUserByEmail(@PathVariable String email) {
		AppUser user = userService.findUserByEmail(email);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
