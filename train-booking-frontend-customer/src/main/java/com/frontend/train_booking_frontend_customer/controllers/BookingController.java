package com.frontend.train_booking_frontend_customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frontend.train_booking_frontend_customer.models.Booking;
import com.frontend.train_booking_frontend_customer.services.impl.BookingService;


@Controller
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("page", "booking");
		
		return "booking/index";
	}
	
	@GetMapping("/show")
	public String show(@RequestParam(required = false) String code, 
	                   Model model, RedirectAttributes redirectAttributes) {
	    if (code == null || code.trim().isEmpty()) {
	        redirectAttributes.addFlashAttribute("error", "Vui lòng nhập mã đặt vé.");
	        return "redirect:/booking/index"; 
	    }

	    Booking booking = bookingService.findByCode(code);

	    if (booking == null) {
	        redirectAttributes.addFlashAttribute("error", "Không tìm thấy thông tin đặt vé.");
	        return "redirect:/booking/index";
	    }

	    model.addAttribute("page", "booking")
	         .addAttribute("booking", booking)
	         .addAttribute("success", "Tra cứu đơn đặt vé thành công!");

	    return "booking/show"; 
	}

}
