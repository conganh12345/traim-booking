package com.frontend.train_booking_frontend_customer.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frontend.train_booking_frontend_customer.models.Route;
import com.frontend.train_booking_frontend_customer.models.Schedule;
import com.frontend.train_booking_frontend_customer.services.IScheduleService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
//	@GetMapping("/index")
//	public String index(Model model, @Valid String depature, @Valid String destination, Date dateFrom) {
//		return "schedule/index";
//	}
	
	@Autowired
	private IScheduleService scheduleService;
	
	@PostMapping("/index")
    public String index(@Valid @ModelAttribute("route") Route route,
    					@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
    					BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("heello");
		if(result.hasErrors()) {
			model.addAttribute("page", "route");
			return "dashboard/index";
		}
		
        List<Schedule> schedules = scheduleService.GetScheduleByLocationAndTime(route.getDepartureLocation(), route.getDestinationLocation(), dateFrom);

        model.addAttribute("page", "schedule")
        	.addAttribute("schedules", schedules);
        return "schedule/index";
    }
}
