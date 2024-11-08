package com.frontend.train_booking_frontend_customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.frontend.train_booking_frontend_customer.models.LoginRequest;
import com.frontend.train_booking_frontend_customer.models.User;
import com.frontend.train_booking_frontend_customer.models.enums.ERole;
import com.frontend.train_booking_frontend_customer.services.IAuthService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private IAuthService authService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/base-layout")
	public String showBaseLayout() {
		return "redirect:/dashboard/index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "auth/signIn";
	}
	
	@PostMapping("/check-login")
	public ResponseEntity<String> handleLogin(@RequestParam String email, @RequestParam String password) {
		LoginRequest loginRequest = new LoginRequest(email, password);
		User user = authService.login(loginRequest);
		if (user != null) {
			if(user.getRole() == ERole.USER) {
				session.setAttribute("loggedInUser", user);
				return ResponseEntity.ok("Đăng nhập thành công!");
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bạn không được đăng nhập vào trang web người dùng");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sai email hoặc mật khẩu.");
		}
	}
	
	@PostMapping("/check-email-exist")
	public ResponseEntity<String> checkEmailExist(@RequestParam String email) {
		User user = authService.getUserByEmail(email);
		if (user != null) {
			return ResponseEntity.ok("Email đã tồn tại");
		} else {
			return null;
		}
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "auth/signup";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute User user) {
		user.setRole(ERole.USER);
		authService.signUp(user);
		return "auth/signIn";
	}
}

