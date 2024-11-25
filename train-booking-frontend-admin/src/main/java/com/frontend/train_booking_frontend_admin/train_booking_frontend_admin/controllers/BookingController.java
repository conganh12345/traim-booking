package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.controllers;

import java.util.List;
import java.util.Map;

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

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Schedule;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Booking;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.User;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.BookingService;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.UserService;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.ScheduleService;


import jakarta.validation.Valid;

@Controller
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<Booking> bookings = bookingService.getAllBookings();
		if(bookings == null) {
			return "auth/signIn";
		}

		model.addAttribute("page", "booking").addAttribute("bookings", bookings);

		return "booking/index";
	}

	@GetMapping("/show/{id}")
	public String show(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		Booking booking = bookingService.getBookingById(id);

		model.addAttribute("page", "booking")
			.addAttribute("booking", booking);

		return "booking/show";
	}
	
	 @GetMapping("/statistics")
	    public ResponseEntity<Map<String, Integer>> getBookingStatistics() {
	        Map<String, Integer> statistics = bookingService.getBookingStatistics();

	        return ResponseEntity.ok(statistics);
	    }
}