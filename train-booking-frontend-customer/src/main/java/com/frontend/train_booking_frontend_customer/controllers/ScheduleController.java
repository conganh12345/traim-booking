package com.frontend.train_booking_frontend_customer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frontend.train_booking_frontend_customer.dtos.SearchSchedule;
import com.frontend.train_booking_frontend_customer.models.Booking;
import com.frontend.train_booking_frontend_customer.models.Coach;
import com.frontend.train_booking_frontend_customer.models.Schedule;
import com.frontend.train_booking_frontend_customer.models.enums.Province;
import com.frontend.train_booking_frontend_customer.services.ICoachService;
import com.frontend.train_booking_frontend_customer.services.IScheduleService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	@Autowired
	private IScheduleService scheduleService;
	
	@Autowired
	private ICoachService coachService;
	
	
	@PostMapping("/index")
    public String index(@Valid @ModelAttribute("searchSchedule") SearchSchedule searchSchedule,
    					BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("page", "searchSchedule")
	        .addAttribute("provinces", Province.values());
			return "dashboard/index";
		}
		
        List<Schedule> schedules = scheduleService.GetScheduleByLocationAndTime(
        		searchSchedule.getDepartureLocation(), 
        		searchSchedule.getDestinationLocation(), 
        		searchSchedule.getDepartureDate()
    		);

        model.addAttribute("page", "schedule")
        	.addAttribute("schedules", schedules);
        return "schedule/index";
    }
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
	    Schedule schedule = scheduleService.findById(id);
	    
	    int trainId = schedule.getRoute().getTrain().getId(); 
	    List<Coach> coaches = coachService.getCoachesByTrainId(trainId);
	    
	    model.addAttribute("schedule", schedule);
	    model.addAttribute("page", "searchSchedule")
	        .addAttribute("searchSchedule", new SearchSchedule())
	        .addAttribute("provinces", Province.values())
	        .addAttribute("coaches", coaches);


	    return "schedule/show"; 
	}

}
