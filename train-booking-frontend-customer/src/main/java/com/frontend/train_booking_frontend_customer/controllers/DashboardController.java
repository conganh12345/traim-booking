package com.frontend.train_booking_frontend_customer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frontend.train_booking_frontend_customer.dtos.SearchSchedule;
import com.frontend.train_booking_frontend_customer.models.Route;
import com.frontend.train_booking_frontend_customer.models.enums.Province;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("page", "dashboard");
		
		model.addAttribute("page", "searchSchedule")
	        .addAttribute("searchSchedule", new SearchSchedule())
	        .addAttribute("provinces", Province.values());
		
		return "dashboard/index";
	}
}
