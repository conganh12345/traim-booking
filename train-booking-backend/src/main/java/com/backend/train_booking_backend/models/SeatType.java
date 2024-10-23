package com.backend.train_booking_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "seat_type")
public class SeatType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String seatTypeName;

	@Column
	private int seatPosition;

	@Column
	private int status;

	@ManyToOne
	@JoinColumn(name = "coach_id")
	@JsonBackReference 
	private Coach coach;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSeatTypeName() {
		return seatTypeName;
	}

	public void setSeatTypeName(String seatTypeName) {
		this.seatTypeName = seatTypeName;
	}

	public int getSeatPosition() {
		return seatPosition;
	}

	public void setSeatPosition(int seatPosition) {
		this.seatPosition = seatPosition;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}
}
