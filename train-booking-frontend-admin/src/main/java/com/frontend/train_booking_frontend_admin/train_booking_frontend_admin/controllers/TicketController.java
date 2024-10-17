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
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Ticket;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.TicketStatus;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.TicketService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	private TicketService ticketService;

	@GetMapping("/index")
	public String index(Model model) {
		List<Ticket> tickets = ticketService.getAllTickets();

		model.addAttribute("page", "ticket").addAttribute("tickets", tickets);

		return "ticket/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("page", "ticket")
			.addAttribute("ticket", new Ticket())
			.addAttribute("ticketStatuses", TicketStatus.values());

		return "ticket/create";
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("page", "ticket");
			
	        return "ticket/create"; 
	    }
		if (ticketService.addTicket(ticket)) {
			redirectAttributes.addFlashAttribute("success", "Thêm mới vé thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Thêm mới vé thất bại!");
		}
		return "redirect:/ticket/index";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		Ticket ticket = ticketService.getTicketById(id);

		model.addAttribute("page", "ticket")
			.addAttribute("ticket", ticket)
			.addAttribute("ticketStatuses", TicketStatus.values());

		return "ticket/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Integer id,@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult result,
			RedirectAttributes redirectAttributes, Model model) {
		ticket.setId(id);
		if (result.hasErrors()) {
			model.addAttribute("page", "ticket");
			
	        return "ticket/edit"; 
	    }
		if (ticketService.updateTicket(ticket)) {
			redirectAttributes.addFlashAttribute("success", "Cập nhật vé thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Cập nhật vé thất bại!");
		}
		return "redirect:/ticket/index";
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteticket(@PathVariable Integer id) {
		if (ticketService.deleteTicket(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa vé.");
		}
	}
}
