package com.backend.train_booking_backend.models;

import java.util.ArrayList;
import java.util.List;

import com.backend.train_booking_backend.models.enums.TicketStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String ticketname;

	@Column
	private double price;

	@Enumerated(EnumType.STRING)
	private TicketStatus status;

	@OneToMany(mappedBy = "ticket")
	@JsonManagedReference
	private List<TicketBookingDetail> details = new ArrayList<>();

	public List<TicketBookingDetail> getDetails() {
		return details;
	}

	public void setDetails(List<TicketBookingDetail> details) {
		this.details = details;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTicketname() {
		return ticketname;
	}

	public void setTicketname(String ticketname) {
		this.ticketname = ticketname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}
}
