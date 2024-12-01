package com.backend.train_booking_backend.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.train_booking_backend.dtos.BookingRequest;
import com.backend.train_booking_backend.models.Booking;
import com.backend.train_booking_backend.services.ITrainService;
import com.backend.train_booking_backend.services.impl.BookingService;
import com.backend.train_booking_backend.services.impl.VNPAYService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@Validated
@RequestMapping("/api/vnpay")
public class VNPAYController {
	
	@Autowired
	private VNPAYService vnpayService;
	
	@Autowired
	private BookingService bookingService;
	
	Booking booking;
	
	@PostMapping("/create_payment/{bookingId}")
    public String submidOrder(HttpServletRequest request, @PathVariable("bookingId") int bookingId) {		
		this.booking = bookingService.findById(bookingId);
		String result = vnpayService.createOrder(request, Long.parseLong(booking.getTotalPrice()), "Đặt vé");
		
		return result;
    }

    // Sau khi hoàn tất thanh toán, VNPAY sẽ chuyển hướng trình duyệt về URL này
	@GetMapping("/payment-return")
	@ResponseBody
	public ResponseEntity<?> paymentCompleted(HttpServletRequest request) {
	    try {
	        Booking completedBooking = vnpayService.orderReturn(request, this.booking);
	        
	        if (completedBooking == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Thanh toán không thành công.");
	        }

	        return ResponseEntity.ok(Map.of(
	            "message", "Thanh toán thành công! Vui lòng quay trở lại website để hoàn tất đơn hàng!",
	            "bookingCode", completedBooking.getCode(),
	            "totalPrice", completedBooking.getTotalPrice()
	        ));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Đã xảy ra lỗi trong quá trình xử lý thanh toán.");
	    }
	}

}
