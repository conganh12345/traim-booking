package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.CoachStatus;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.TrainStatus;

import jakarta.validation.constraints.AssertFalse.List;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Train {
	private Integer id;
	
	@NotEmpty(message = "Tên tàu không được để trống")
    @Size(min = 3, max = 50, message = "Tên tàu phải có độ dài từ 3 đến 50 ký tự")
    private String trainName;

    private String description;
    
	@NotNull(message = "Trạng thái không được để trống")
	private TrainStatus status;

	public Integer getId() {
		return id;
	}
			
	public void setId(Integer id) {
		this.id = id;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TrainStatus getStatus() {
		return status;
	}

	public void setStatus(TrainStatus status) {
		this.status = status;
	}
}
