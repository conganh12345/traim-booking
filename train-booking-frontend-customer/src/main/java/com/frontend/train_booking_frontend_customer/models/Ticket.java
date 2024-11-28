package com.frontend.train_booking_frontend_customer.models;


import com.frontend.train_booking_frontend_customer.models.enums.TicketStatus;

public class Ticket {
	private Integer id;

    private String ticketName;

    private double priceTicket;

    private TicketStatus status;
	
    private Integer bookingId;  	
	private Booking booking;
	
    private Integer seatId;  	
	private Seat seat;
	
	private String customerName;
	
	private String customerIdentify;

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

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerIdentify() {
		return customerIdentify;
	}

	public void setCustomerIdentify(String customerIdentify) {
		this.customerIdentify = customerIdentify;
	}
	
	
	
	
}
