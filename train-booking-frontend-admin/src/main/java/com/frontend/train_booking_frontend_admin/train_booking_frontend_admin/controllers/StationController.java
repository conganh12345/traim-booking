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

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Station;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Route;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.RouteService;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.StationService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/station")
public class StationController {
	
	@Autowired
	private StationService stationService;	
	
	@Autowired
	private RouteService routeService;	

	@GetMapping("/index")
	public String index(Model model) {
		List<Station> stations = stationService.getAllStations();
		if(stations == null) {
			return "auth/signIn";
		}

		model.addAttribute("page", "station").addAttribute("stations", stations);

		return "station/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
	    List<Route> routes = routeService.getAllRoutes();
	    
	    model.addAttribute("page", "station")
	         .addAttribute("routes", routes)
	         .addAttribute("station", new Station());

	    return "station/create";
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("station") Station station, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("page", "station");
			
	        return "station/create"; 
	    }
		Route route = routeService.getRouteById(station.getRouteId());
		
		station.setRoute(route);
		if (stationService.addStation(station)) {
			redirectAttributes.addFlashAttribute("success", "Thêm mới nhà ga thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Thêm mới nhà ga thất bại!");
		}
		return "redirect:/station/index";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		Station station = stationService.getStationById(id);
	    List<Route> routes = routeService.getAllRoutes();

	    model.addAttribute("page", "station")
	         .addAttribute("station", station)
	         .addAttribute("routes", routes);

	    return "station/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Integer id,@Valid @ModelAttribute("station") Station station, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
		station.setId(id);
		if (result.hasErrors()) {
			model.addAttribute("page", "station");
			
	        return "station/edit"; 
	    }
		
		Route route = routeService.getRouteById(station.getRouteId());
		
		station.setRoute(route);
		if (stationService.updateStation(station)) {
			redirectAttributes.addFlashAttribute("success", "Cập nhật nhà ga thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Cập nhật nhà ga thất bại!");
		}
		return "redirect:/station/index";
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteStation(@PathVariable Integer id) {
		if (stationService.deleteStation(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa nhà ga.");
		}
	}
}