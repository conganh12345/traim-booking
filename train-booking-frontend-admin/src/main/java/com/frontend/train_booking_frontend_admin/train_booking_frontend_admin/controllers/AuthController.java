package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.LoginRequest;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.User;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.UserService;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.IService.IAuthService;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.ERole;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private IAuthService authService;
	
	@Autowired
	private UserService userService;
	
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
		session.setAttribute("loginEmail", loginRequest.getEmail());
		String token = authService.login(loginRequest);
		if (token == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sai email hoặc mật khẩu.");
		} 
		session.setAttribute("token", token);
		User user = userService.userProfile();
		if(user != null) {
			if(user.getRole() == ERole.ADMIN) {
				return ResponseEntity.ok("Đăng nhập thành công!");
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tài khoản của bạn không thể đăng nhập vào trang này");
		}else {
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
	
	@PostMapping("/logout")
	public String logout() {
		// HUy session
		session.invalidate();
		return "auth/signIn";
	}
}