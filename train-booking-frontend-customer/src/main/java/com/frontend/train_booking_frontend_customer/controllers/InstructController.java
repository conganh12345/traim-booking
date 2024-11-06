package com.frontend.train_booking_frontend_customer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instruct")
public class InstructController {
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("page", "instruct");
		
		return "instruct/index";
	}
}
