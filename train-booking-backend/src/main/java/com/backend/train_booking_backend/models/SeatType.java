package com.backend.train_booking_backend.models;

import java.util.ArrayList;
import java.util.List;

import com.backend.train_booking_backend.models.enums.ERole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.backend.train_booking_backend.models.enums.SeatTypeStatus;

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
@Table(name = "seattype")
public class SeatType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String seatName;

	@Column
	private double price;
	
	@Enumerated(EnumType.STRING)
	private SeatTypeStatus status;

	@OneToMany(mappedBy = "seatType")
	@JsonIgnore
	private List<Seat> seat = new ArrayList<>();

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

	public List<Seat> getSeat() {
		return seat;
	}

	public void setSeat(List<Seat> seat) {
		this.seat = seat;
	}
}
