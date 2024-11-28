package com.frontend.train_booking_frontend_customer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frontend.train_booking_frontend_customer.dtos.BookingRequest;
import com.frontend.train_booking_frontend_customer.models.Booking;
import com.frontend.train_booking_frontend_customer.models.Schedule;
import com.frontend.train_booking_frontend_customer.models.Seat;
import com.frontend.train_booking_frontend_customer.models.Ticket;
import com.frontend.train_booking_frontend_customer.models.User;
import com.frontend.train_booking_frontend_customer.services.IBookingService;
import com.frontend.train_booking_frontend_customer.services.ITicketService;
import com.frontend.train_booking_frontend_customer.services.IUserService;
import com.frontend.train_booking_frontend_customer.services.impl.BookingService;


@Controller
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	private IBookingService bookingService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ITicketService ticketService;
	
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("page", "booking");
		
		return "booking/index";
	}
	
	@GetMapping("/show")
	public String show(@RequestParam(required = false) String code, 
	                   Model model, RedirectAttributes redirectAttributes) {
	    if (code == null || code.trim().isEmpty()) {
	        redirectAttributes.addFlashAttribute("error", "Vui lòng nhập mã đặt vé.");
	        return "redirect:/booking/index"; 
	    }

	    Booking booking = bookingService.findByCode(code);

	    if (booking == null) {
	        redirectAttributes.addFlashAttribute("error", "Không tìm thấy thông tin đặt vé.");
	        return "redirect:/booking/index";
	    }

	    model.addAttribute("page", "booking")
	         .addAttribute("booking", booking)
	         .addAttribute("success", "Tra cứu đơn đặt vé thành công!");

	    return "booking/show"; 
	}
	
	@GetMapping("/cart-info")
	public String cartInfo(Model model) {
		User user = userService.userProfile();
        if (user == null) {
            return "auth/signIn"; 
        }
        

		model.addAttribute("page", "cart-info")
        	.addAttribute("page", "user")
            .addAttribute("user", user);
		
		return "booking/cart-info";
	}
	
	@PostMapping("/book-confirm")
	public String bookConfirm(@RequestBody BookingRequest bookingRequest, Model model) {
	    
	    
	    User user = userService.userProfile();
        if (user == null) {
            return "auth/signIn"; 
        }

	    Booking booking = bookingRequest.getBooking();
	    if(booking.getSchedule() == null) {
	    	Schedule schedule = new Schedule();
	    	booking.setSchedule(schedule);
	    	booking.getSchedule().setId(booking.getScheduleId());
	    }
	    
	    booking.setUser(user);
	    
	    Booking booking2 = bookingService.insertBooking(booking);
	    if(booking2 != null) {
	    	List<Ticket> tickets = bookingRequest.getTickets();
		    
		    for (Ticket ticket : tickets) {
		        if (ticket.getSeat() == null) {
		        	Seat seat = new Seat();
		            ticket.setSeat(seat);
		            ticket.getSeat().setId(ticket.getSeatId()); 
		            ticket.setBooking(booking2);
		        } else {
		            System.out.println("Seat is null for ticket: " + ticket);
		        }
		    }
		    
		    if(ticketService.addTickets(tickets)) {
		    	System.out.println("Add tickets successfull");
		    }
	    }

	    return "instruct/index"; 
	}


	
	@GetMapping("/book-confirm")
	public String showBookingForm() {
	    return "redirect:/instruct/index"; 
	}



}
