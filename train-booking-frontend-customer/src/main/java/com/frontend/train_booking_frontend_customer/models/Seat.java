package com.frontend.train_booking_frontend_customer.models;


public class Seat {
	private Integer id;
	
	private String seatName;
	
	private SeatType seatType;
	
	private Coach coach;

	public Integer getId() {
		return id;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	public Integer getSeatTypeId() {
		return seatTypeId;
	}
	public void setSeatTypeId(Integer seatTypeId) {
		this.seatTypeId = seatTypeId;
	}
	public SeatType getSeatType() {
		return seatType;
	}
	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}
	public Integer getCoachId() {
		return coachId;
	}
	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
	}
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	
}
