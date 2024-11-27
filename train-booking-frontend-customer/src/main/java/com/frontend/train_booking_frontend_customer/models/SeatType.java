package com.frontend.train_booking_frontend_customer.models;

import com.frontend.train_booking_frontend_customer.models.enums.SeatTypeStatus;

public class SeatType {
	private Integer id;
	
	private String seatName;
	
	private double price;
	
	private SeatTypeStatus status;


	public SeatTypeStatus getStatus() {
		return status;
	}

	public void setStatus(SeatTypeStatus status) {
		this.status = status;
	}

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
	
}
