package com.backend.train_booking_backend.models;

import com.backend.train_booking_backend.models.enums.TicketStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String ticketName;

	@Column
	private double priceTicket;

	@Column
	private int seatPosition;

	@Enumerated(EnumType.STRING)
	private TicketStatus status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id")
	private Booking booking;


	@OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Seat seat;


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


	public TicketStatus getStatus() {
		return status;
	}


	public void setStatus(TicketStatus status) {
		this.status = status;
	}


	public Booking getBooking() {
		return booking;
	}


	public void setBooking(Booking booking) {
		this.booking = booking;
	}


	public Seat getSeat() {
		return seat;
	}


	public void setSeat(Seat seat) {
		this.seat = seat;
	}
}
