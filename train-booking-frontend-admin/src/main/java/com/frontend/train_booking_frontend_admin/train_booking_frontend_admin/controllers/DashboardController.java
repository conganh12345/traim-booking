package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Schedule;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Train;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.User;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.ScheduleService;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.TrainService;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.UserService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
	private TrainService trainService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<Schedule> schedules = scheduleService.getAllSchedules();
		List<User> users = userService.getAllUsers();
		List<Train> trains = trainService.getAllTrains();
		
		long trainCount = trains.size();
        long scheduleCount = schedules.size();
        long userCount = users.size();

        model.addAttribute("trainCount", trainCount);
        model.addAttribute("scheduleCount", scheduleCount);
        model.addAttribute("userCount", userCount);
		model.addAttribute("page", "dashboard");
		
		return "dashboard/index";
	}
}
