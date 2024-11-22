package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;


import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.CoachStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Seat {
	private Integer id;
	
	@NotEmpty(message = "Tên ghế không được để trống")
    @Size(min = 3, max = 50, message = "Tên ghế phải có độ dài từ 3 đến 50 ký tự")
    private String seatName;
	
	@NotNull(message = "Loại ghế không được để trống")
    private Integer seatTypeId;  	
	private SeatType seatType;
	
	@NotNull(message = "Toa không được để trống")
    private Integer coachId;  	
	private Coach coach;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSeatName() {
		return seatName;
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
	public void setSeatName(String seatName) {
		this.seatName = seatName;
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
