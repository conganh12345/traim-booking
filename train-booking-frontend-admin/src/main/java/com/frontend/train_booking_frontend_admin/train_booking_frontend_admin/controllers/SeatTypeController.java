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
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.SeatType;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.SeatTypeStatus;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.SeatTypeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/seattype")
public class SeatTypeController {
	@Autowired
	private SeatTypeService seattypeService;

	@GetMapping("/index")
	public String index(Model model) {
		List<SeatType> seattypes = seattypeService.getAllSeatTypes();
		if(seattypes == null) {
			return "auth/signIn";
		}

		model.addAttribute("page", "seattype").addAttribute("seattypes", seattypes);

		return "seattype/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("page", "seattype")
			.addAttribute("seattype", new SeatType())
			.addAttribute("seattypeStatus", SeatTypeStatus.values());

		return "seattype/create";
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("seattype") SeatType seattype, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("page", "seattype");
			
	        return "seattype/create"; 
	    }
		if (seattypeService.addSeatType(seattype)) {
			redirectAttributes.addFlashAttribute("success", "Thêm mới loại ghế thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Thêm mới loại ghế thất bại!");
			return "auth/signIn";
		}
		return "redirect:/seattype/index";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		SeatType seattype = seattypeService.getSeatTypeById(id);

		model.addAttribute("page", "seattype")
			.addAttribute("seattype", seattype)
			.addAttribute("seattypeStatus", SeatTypeStatus.values());

		return "seattype/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Integer id, @Valid @ModelAttribute("seattype") SeatType seattype, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("page", "seattype");
			
	        return "seattype/edit"; 
	    }
		seattype.setId(id);
		if (seattypeService.updateSeatType(seattype)) {
			redirectAttributes.addFlashAttribute("success", "Cập nhật loại ghế thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Cập nhật loại ghế thất bại!");
		}
		return "redirect:/seattype/index";
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteSeatType(@PathVariable Integer id) {
		if (seattypeService.deleteSeatType(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa loại ghế.");
		}
	}
}