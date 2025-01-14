package com.frontend.train_booking_frontend_customer.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.frontend.train_booking_frontend_customer.models.Coach;
import com.frontend.train_booking_frontend_customer.models.Schedule;
import com.frontend.train_booking_frontend_customer.models.Seat;
import com.frontend.train_booking_frontend_customer.models.enums.Province;
import com.frontend.train_booking_frontend_customer.services.ICoachService;
import com.frontend.train_booking_frontend_customer.services.IScheduleService;
import com.frontend.train_booking_frontend_customer.services.ISeatService;
import com.frontend.train_booking_frontend_customer.services.ITicketService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/seat")
public class SeatController {

	@Autowired
	private ISeatService seatService;
	
	@Autowired
	private ITicketService ticketService;
	
	
	@GetMapping("/list/{coachId}/{scheduleId}")
	public ResponseEntity<?> getSeatByCoachIdAndScheduleId(
	        @PathVariable("coachId") int coachId, 
	        @PathVariable("scheduleId") int scheduleId) {
	    try {
	        List<Seat> seats = seatService.getSeatesByCoachId(coachId);

	        List<Integer> bookedSeatIds = ticketService.getBookedSeats(scheduleId);

	        Map<String, Object> response = new HashMap<>();
	        response.put("seats", seats);
	        response.put("bookedSeats", bookedSeatIds);

	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        return ResponseEntity.status(500).body("Không thể tải danh sách ghế. Vui lòng thử lại.");
	    }
	}


}