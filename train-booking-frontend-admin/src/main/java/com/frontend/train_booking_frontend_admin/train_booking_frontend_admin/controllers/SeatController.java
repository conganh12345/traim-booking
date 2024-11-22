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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Coach;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Seat;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.SeatType;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Train;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.CoachService;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.SeatService;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.SeatTypeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/seat")
public class SeatController {
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private CoachService coachService;
	
	@Autowired
	private SeatTypeService seatTypeService;

	@GetMapping("/index")
	public String index(Model model) {
		List<Seat> seats = seatService.getAllSeats();
		if(seats == null) {
			return "auth/signIn";
		}

		model.addAttribute("page", "seat").addAttribute("seats", seats);

		return "seat/index";
	}

	@GetMapping("/create")
	public String create(@RequestParam("coachId") Integer coachId, Model model) {
		List<SeatType> seatTypes = seatTypeService.getAllSeatTypes();
		model.addAttribute("page", "seat")
			.addAttribute("seat", new Seat())
			.addAttribute("coachId", coachId)
			.addAttribute("seatTypes", seatTypes);

		return "seat/create";
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("seat") Seat seat, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("page", "seat");
			
	        return "seat/create"; 
	    }
		Coach coach = coachService.getCoachById(seat.getCoachId());
		SeatType seatType = seatTypeService.getSeatTypeById(seat.getSeatTypeId());
		seat.setSeatType(seatType);
		seat.setCoach(coach);
		if (seatService.addSeat(seat)) {
			redirectAttributes.addFlashAttribute("success", "Thêm mới ghế thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Thêm mới ghế thất bại!");
			return "auth/signIn";
		}
		return "redirect:/coach/show/" + seat.getCoachId();
	}

	@GetMapping("/edit/{id}")
	public String edit(@RequestParam("coachId") Integer coachId, @PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		Seat seat = seatService.getSeatById(id);
		List<SeatType> seatTypes = seatTypeService.getAllSeatTypes();
		model.addAttribute("page", "seat")
			.addAttribute("seat", seat)
			.addAttribute("coachId", coachId)
			.addAttribute("seatTypes", seatTypes);


		return "seat/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Integer id, @Valid @ModelAttribute("seat") Seat seat, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("page", "seat");
			
	        return "seat/edit"; 
	    }
		Coach coach = coachService.getCoachById(seat.getCoachId());
		SeatType seatType = seatTypeService.getSeatTypeById(seat.getSeatTypeId());
		seat.setSeatType(seatType);
		seat.setCoach(coach);
		if (seatService.updateSeat(seat)) {
			redirectAttributes.addFlashAttribute("success", "Cập nhật ghế thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Cập nhật ghế thất bại!");
		}
		return "redirect:/coach/show/" + seat.getCoachId();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteSeat(@PathVariable Integer id) {
		if (seatService.deleteSeat(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa ghế.");
		}
	}
}