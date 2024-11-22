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

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Train;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.CoachStatus;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.Province;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Route;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Schedule;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.TrainService;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.RouteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/route")
public class RouteController {
	@Autowired
	private RouteService routeService;
	
	@Autowired
	private TrainService trainService;	

	@GetMapping("/index")
	public String index(Model model) {
		List<Route> routes = routeService.getAllRoutes();
		if(routes == null) {
			return "auth/signIn";
		}

		model.addAttribute("page", "route").addAttribute("routes", routes);

		return "route/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
	    List<Train> trains = trainService.getAllTrains();
	    
	    model.addAttribute("page", "route")
	         .addAttribute("trains", trains)
	         .addAttribute("route", new Route())
	         .addAttribute("provinces", Province.values());

	    return "route/create";
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("route") Route route, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("page", "route");
			
	        return "route/create"; 
	    }
		Train train = trainService.getTrainById(route.getTrainId());
		
		route.setTrain(train);
		if (routeService.addRoute(route)) {
			redirectAttributes.addFlashAttribute("success", "Thêm mới lịch trình thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Thêm mới lịch trình thất bại!");
		}
		return "redirect:/route/index";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
	    Route route = routeService.getRouteById(id);
	    List<Train> trains = trainService.getAllTrains();

	    model.addAttribute("page", "route")
	         .addAttribute("route", route)
	         .addAttribute("trains", trains)
	         .addAttribute("provinces", Province.values());

	    return "route/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Integer id,@Valid @ModelAttribute("route") Route route, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
		route.setId(id);
		if (result.hasErrors()) {
			model.addAttribute("page", "route");
			
	        return "route/edit"; 
	    }
		
		Train train = trainService.getTrainById(route.getTrainId());
		
		route.setTrain(train);
		if (routeService.updateRoute(route)) {
			redirectAttributes.addFlashAttribute("success", "Cập nhật lịch trình thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Cập nhật lịch trình thất bại!");
		}
		return "redirect:/route/index";
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRoute(@PathVariable Integer id) {
		if (routeService.deleteRoute(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa lịch trình.");
		}
	}
}