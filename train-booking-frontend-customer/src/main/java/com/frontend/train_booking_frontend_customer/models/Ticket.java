package com.frontend.train_booking_frontend_customer.models;

public class Ticket {
	private Integer id;
	
	private String ticketName;
	
	private double priceTicket;
	
	private int seatPosition;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public double getPriceTicket() {
		return priceTicket;
	}

	public void setPriceTicket(double priceTicket) {
		this.priceTicket = priceTicket;
	}

	public int getSeatPosition() {
		return seatPosition;
	}

	public void setSeatPosition(int seatPosition) {
		this.seatPosition = seatPosition;
	}
	
}
