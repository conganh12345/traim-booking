package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.User;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.ERole;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;


	@GetMapping("/index")
	public String index(Model model) {
		List<User> users = userService.getAllUsers();
		if (users == null) {
			return "auth/signIn";
		}

		model.addAttribute("page", "user").addAttribute("users", users);

		return "user/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("page", "user").addAttribute("user", new User()).addAttribute("userRoles", ERole.values());

		return "user/create";
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("page", "user");

			return "user/create";
		}
		if (userService.addUser(user)) {
			redirectAttributes.addFlashAttribute("success", "Thêm mới người dùng thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Thêm mới người dùng thất bại!");
			return "auth/signIn";
		}
		return "redirect:/user/index";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		User user = userService.getUserById(id);

		model.addAttribute("page", "user").addAttribute("user", user).addAttribute("userRoles", ERole.values());

		return "user/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Integer id, @Valid @ModelAttribute("user") User user, BindingResult result,
			RedirectAttributes redirectAttributes, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("page", "user");

			return "user/edit";
		}
		user.setId(id);
		if (userService.updateUser(user)) {
			redirectAttributes.addFlashAttribute("success", "Cập nhật người dùng thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Cập nhật người dùng thất bại!");
		}
		return "redirect:/user/index";
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
		if (userService.deleteUser(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa người dùng.");
		}
	}

	@GetMapping("/profile")
	public String profile(Model model) {
		User user = userService.userProfile();
		if(user == null) {
			return "/auth/signIn";
		}
		
		model.addAttribute("userLogin", user);

		return "/user/profile";
	}
	
//	@GetMapping("/user-account-setting")
//	public String userAcocuntSetting(Model model) {
//		User user = userService.userProfile();
//		if(user == null) {
//			return "/auth/signIn";
//		}
//		
//		model.addAttribute("userLogin", user);
//		
//		return "/user/user-account-setting";
//	}
//	
//	@PostMapping("/updateProfile/{id}")
//	public String updateProfile(@PathVariable Integer id, @Valid @ModelAttribute("user") User user, BindingResult result,
//	                     RedirectAttributes redirectAttributes, Model model) {
//	    if (result.hasErrors()) {
//	        model.addAttribute("page", "user");
//	        return "user/user-account-setting"; // Trở lại trang chỉnh sửa nếu có lỗi
//	    }
//
//	    user.setId(id);  // Đảm bảo rằng ID của người dùng được set đúng
//	    if (userService.updateUser(user)) {
//	        redirectAttributes.addFlashAttribute("success", "Cập nhật người dùng thành công!");
//	    } else {
//	        redirectAttributes.addFlashAttribute("error", "Cập nhật người dùng thất bại!");
//	    }
//
//	    return "redirect:/user/profile";  // Sau khi cập nhật, chuyển hướng lại trang hồ sơ
//	}


}