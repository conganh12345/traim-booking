//package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.controllers;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Booking;
//import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Coach;
//import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.SeatType;
//import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Ticket;
//import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Train;
//import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.CoachStatus;
//import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.TicketStatus;
//import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.CoachService;
//import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.SeatTypeService;
//import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.TrainService;
//
//import jakarta.validation.Valid;
//
//@Controller
//@RequestMapping("/coach")
//public class CoachController {
//	@Autowired
//	private CoachService coachService;
//	@Autowired
//	private TrainService trainService;
//	@Autowired
//	private SeatTypeService seattypeService;
//
//	@GetMapping("/index")
//	public String index(Model model) {
//		List<Coach> coachs = coachService.getAllCoachs();
//
//		model.addAttribute("page", "coach").addAttribute("coachs", coachs);
//
//		return "coach/index";
//	}
//
//	@GetMapping("/create")
//	public String create(Model model) {
//		List<Train> trains = trainService.getAllTrains();
//		
//		model.addAttribute("page", "coach")
//			.addAttribute("trains", trains)
//			.addAttribute("coach", new Coach())
//			.addAttribute("coachStatuses", CoachStatus.values());
//
//		return "coach/create";
//	}
//
//	@PostMapping("/create")
//	public String create(@Valid @ModelAttribute("coach") Coach coach, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
//		if (result.hasErrors()) {
//			model.addAttribute("page", "coach");
//			
//	        return "coach/create"; 
//	    }
//		Train train = trainService.getTrainById(coach.getTrainId());
//		
//		coach.setTrain(train);
//		if (coachService.addCoach(coach)) {
//			redirectAttributes.addFlashAttribute("success", "Thêm mới toa thành công!");
//		} else {
//			redirectAttributes.addFlashAttribute("error", "Thêm mới toa thất bại!");
//		}
//		return "redirect:/coach/index";
//	}
//
//	@GetMapping("/edit/{id}")
//	public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
//		Coach coach = coachService.getCoachById(id);
//		List<Train> trains = trainService.getAllTrains();
//
//		model.addAttribute("page", "coach")
//			.addAttribute("coach", coach)
//			.addAttribute("trains", trains)
//			.addAttribute("coachStatuses", CoachStatus.values());
//
//		return "coach/edit";
//	}
//
//	@PostMapping("/update/{id}")
//	public String update(@PathVariable Integer id,@Valid @ModelAttribute("coach") Coach coach, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
//		coach.setId(id);
//		if (result.hasErrors()) {
//			model.addAttribute("page", "coach");
//			
//	        return "coach/edit"; 
//	    }
//		
//		Train train = trainService.getTrainById(coach.getTrainId());
//		
//		coach.setTrain(train);
//		if (coachService.updateCoach(coach)) {
//			redirectAttributes.addFlashAttribute("success", "Cập nhật toa thành công!");
//		} else {
//			redirectAttributes.addFlashAttribute("error", "Cập nhật toa thất bại!");
//		}
//		return "redirect:/coach/index";
//	}
//	
//	@GetMapping("/show/{id}")
//	public String show(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
//		Coach coach = coachService.getCoachById(id);
//		List<SeatType> seattypes = seattypeService.getAllSeatTypes();
//
//		model.addAttribute("page", "coach")
//			.addAttribute("coach", coach)
//			.addAttribute("seattypes", seattypes);
//
//		return "coach/show";
//	}
//
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<?> deleteCoach(@PathVariable Integer id) {
//		if (coachService.deleteCoach(id)) {
//			return ResponseEntity.ok().build();
//		} else {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa toa.");
//		}
//	}
//}