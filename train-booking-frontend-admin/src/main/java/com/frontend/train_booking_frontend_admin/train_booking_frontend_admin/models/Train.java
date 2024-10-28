package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.CoachStatus;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.TrainStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Train {
	private Integer id;
	
	@NotEmpty(message = "Tên tàu không được để trống")
    @Size(min = 3, max = 50, message = "Tên tàu phải có độ dài từ 3 đến 50 ký tự")
    private String trainname;

	@NotNull(message = "Tên tàu không được để trống")
    @Min(value = 1, message = "Số lượng toa phải lớn hơn hoặc bằng 1")
    private int coachcount;

    @NotEmpty(message = "Mô tả không được để trống")
    @Size(max = 200, message = "Mô tả không được dài quá 200 ký tự")
    private String description;
    
	@NotNull(message = "Trạng thái không được để trống")
	private TrainStatus status;
	
	public Integer getId() {
		return id;
	}
	public TrainStatus getStatus() {
		return status;
	}
	public void setStatus(TrainStatus status) {
		this.status = status;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTrainname() {
		return trainname;
	}
	public void setTrainname(String trainname) {
		this.trainname = trainname;
	}
	public int getCoachcount() {
		return coachcount;
	}
	public void setCoachcount(int coachcount) {
		this.coachcount = coachcount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
