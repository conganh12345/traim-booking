package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import java.time.LocalDateTime;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.BookingStatus;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Booking {
	private Integer id;
	
	@NotEmpty(message = "Họ và tên không được để trống")
    @Size(min = 3, max = 100, message = "Họ và tên phải có độ dài từ 3 đến 100 ký tự")
    private String fullName;

	@NotEmpty(message = "Phương thức thanh toán không được để trống")
    private int paymentMethod;

	@NotEmpty(message = "Trạng thái không được để trống")
    private BookingStatus status;

    @NotEmpty(message = "Ga khởi hành không được để trống")
    private String depatureStation;

    @NotEmpty(message = "Ga đến không được để trống")
    private String destinationStation;

    @NotEmpty(message = "Thời gian đặt vé không được để trống")
    private LocalDateTime bookingTime;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public BookingStatus getStatus() {
		return status;
	}
	public void setStatus(BookingStatus status) {
		this.status = status;
	}
	public String getDepatureStation() {
		return depatureStation;
	}
	public void setDepatureStation(String depatureStation) {
		this.depatureStation = depatureStation;
	}
	public String getDestinationStation() {
		return destinationStation;
	}
	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}
	public LocalDateTime getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}			
}
