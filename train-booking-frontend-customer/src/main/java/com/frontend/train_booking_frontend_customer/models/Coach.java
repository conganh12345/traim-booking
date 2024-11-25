package com.frontend.train_booking_frontend_customer.models;

import java.util.ArrayList;
import java.util.List;

import com.frontend.train_booking_frontend_customer.models.enums.CoachStatus;

public class Coach {
	private Integer id;

	private String coachName;
	
	private String description;
	
	private CoachStatus status;
	
	private Train train;
	
	private List<Seat> seats = new ArrayList<>();

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Integer getId() {
		return id;
	}

	public CoachStatus getStatus() {
		return status;
	}

	public void setStatus(CoachStatus status) {
		this.status = status;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}