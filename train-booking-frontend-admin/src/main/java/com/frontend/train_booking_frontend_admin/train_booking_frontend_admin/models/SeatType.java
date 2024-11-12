package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.SeatTypeStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SeatType {
	private Integer id;

	@NotEmpty(message = "Tên loại ghế không được để trống")
    @Size(min = 3, max = 50, message = "Tên loại ghế phải có độ dài từ 3 đến 50 ký tự")
    private String seatName;

	@NotNull(message = "Gía ghế không được để trống")
    @Min(value = 1000, message = "Gía ghế phải lớn hơn hoặc bằng 1000")
	private double price;
	
	@NotNull(message = "Trạng thái không được để trống")
    private SeatTypeStatus status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public SeatTypeStatus getStatus() {
		return status;
	}

	public void setStatus(SeatTypeStatus status) {
		this.status = status;
	}
	
}
