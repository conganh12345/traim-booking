package com.frontend.train_booking_frontend_customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.frontend.train_booking_frontend_customer.models.LoginRequest;
import com.frontend.train_booking_frontend_customer.models.User;
import com.frontend.train_booking_frontend_customer.services.impl.IAuthService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private IAuthService authService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/login")
	public String login() {
		return "user/signIn";
	}
	
	@PostMapping("/check-login")
	public ResponseEntity<String> handleLogin(@RequestParam String email, @RequestParam String password) {
		LoginRequest loginRequest = new LoginRequest(email, password);
		User user = authService.login(loginRequest);
		if (user != null) {
			session.setAttribute("loggedInUser", user);
			return ResponseEntity.ok("Đăng nhập thành công!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sai email hoặc mật khẩu.");
		}
	}
}

