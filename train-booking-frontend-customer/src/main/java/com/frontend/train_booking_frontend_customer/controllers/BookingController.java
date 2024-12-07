package com.frontend.train_booking_frontend_customer.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frontend.train_booking_frontend_customer.dtos.BookingRequest;
import com.frontend.train_booking_frontend_customer.models.Booking;
import com.frontend.train_booking_frontend_customer.models.Schedule;
import com.frontend.train_booking_frontend_customer.models.Seat;
import com.frontend.train_booking_frontend_customer.models.Ticket;
import com.frontend.train_booking_frontend_customer.models.User;
import com.frontend.train_booking_frontend_customer.models.enums.BookingStatus;
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
	
	Booking booking = new Booking();
	
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
	    
	    List<Ticket> tickets = ticketService.getTicketsByBookingId(booking.getId());

	    if (booking == null) {
	        redirectAttributes.addFlashAttribute("error", "Không tìm thấy thông tin đặt vé.");
	        return "redirect:/booking/index";
	    }
	    String url = bookingService.getVNPayByBookingId(booking.getId());
	    
	    model.addAttribute("url", url);
	    model.addAttribute("page", "booking")
	         .addAttribute("booking", booking)
	         .addAttribute("tickets", tickets)
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
	@ResponseBody
	public ResponseEntity<?> bookConfirm(@RequestBody BookingRequest bookingRequest) {
	    User user = userService.userProfile();
	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please log in.");
	    }

	    Booking booking = bookingRequest.getBooking();
	    if (booking.getSchedule() == null) {
	        Schedule schedule = new Schedule();
	        schedule.setId(booking.getScheduleId());
	        booking.setSchedule(schedule);
	    }

	    booking.setUser(user);
	    Booking booking2 = bookingService.insertBooking(booking);
	    booking2.setCode("BK-00" + booking2.getId());
	    bookingService.updateBooking(booking2);

	    if (booking2 != null) {
	        List<Ticket> tickets = bookingRequest.getTickets();
	        for (Ticket ticket : tickets) {
	            if (ticket.getSeat() == null) {
	                Seat seat = new Seat();
	                seat.setId(ticket.getSeatId());
	                ticket.setSeat(seat);
	            }
	            ticket.setBooking(booking2);
	        }
	        
	        if (ticketService.addTickets(tickets)) {
	        	System.out.println("Booking với ID không tồn tại.");
	        	return ResponseEntity.ok(Collections.singletonMap("code", booking2.getCode()));

	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Failed to add tickets.");
	        }
	    }

	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body("Failed to create booking.");
	}
	
	
	@GetMapping("/comfirm-order/{code}")
	public String comfirmOrder(@PathVariable String code, 
	                   Model model, RedirectAttributes redirectAttributes) {
	    Booking booking = bookingService.findByCode(code);
	    this.booking = booking;
	    List<Ticket> tickets = ticketService.getTicketsByBookingId(booking.getId());
	    String url = bookingService.getVNPayByBookingId(booking.getId());
	    	    
	    model.addAttribute("url", url);
	    model.addAttribute("page", "booking")
	         .addAttribute("booking", booking)
	         .addAttribute("tickets", tickets)
	         .addAttribute("success", "Đặt vé thành công! Vui lòng kiểm tra lại hóa đơn và tiến hành thanh toán");

	    return "booking/show"; 
	}

	@GetMapping("/payment-success")
	public String handlePaymentSuccess(Model model, RedirectAttributes redirectAttributes) {
	    try {
	        Booking updatedBooking = bookingService.processPayment(this.booking.getId());

	        this.booking = updatedBooking;

	        List<Ticket> tickets = ticketService.getTicketsByBookingId(updatedBooking.getId());

	        model.addAttribute("page", "booking")
	             .addAttribute("booking", updatedBooking)
	             .addAttribute("tickets", tickets)
	             .addAttribute("success", "Thanh toán thành công! Vui lòng kiểm tra email để xem chi tiết thông tin đơn hàng.");

	        return "booking/show";
	    } catch (Exception e) {
	        e.printStackTrace();
	        redirectAttributes.addFlashAttribute("error", "Đã xảy ra LỖI.");
	        return "redirect:/booking/index";
	    }
	}

}
