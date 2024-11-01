package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.SeatTypeStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SeatType {
	private Integer id;

	@NotEmpty(message = "Tên loại ghế không được để trống")
    @Size(min = 3, max = 50, message = "Tên loại ghế phải có độ dài từ 3 đến 50 ký tự")
    private String seatTypeName;

	@NotNull(message = "Vị trí ghế không được để trống")
    @Min(value = 1, message = "Vị trí ghế phải lớn hơn hoặc bằng 1")
    private int seatPosition;

	@NotNull(message = "Trạng thái không được để trống")
    private SeatTypeStatus status;
	
	private Integer coachId;
	
	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	private Coach coach;

	public Integer getCoachId() {
		return coachId;
	}

	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
	}

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

	public SeatTypeStatus getStatus() {
		return status;
	}

	public void setStatus(SeatTypeStatus status) {
		this.status = status;
	}
	
	
}
