package com.backend.train_booking_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.train_booking_backend.models.AppUser;
import com.backend.train_booking_backend.models.LoginRequest;
import com.backend.train_booking_backend.security.JwtUtils;
import com.backend.train_booking_backend.services.IUserService;
import com.backend.train_booking_backend.services.impl.UserDetailsServiceImplement;


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
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        if (userDetails != null && passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            AppUser user = userService.findUserByUsername(loginRequest.getUsername());
            
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

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AppUser user) {
        // Check if the username already exists
        if (userService.findUserByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(400).body("Username already exists");
        }

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        userService.save(user);
        
        return ResponseEntity.ok("User registered successfully");
    }
}

