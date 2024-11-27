package com.frontend.train_booking_frontend_customer.models;

import com.frontend.train_booking_frontend_customer.models.enums.TrainStatus;

public class Train {
	private Integer id;
	
	private String trainName;
	
	private String description;
	
	private TrainStatus status;

	public TrainStatus getStatus() {
		return status;
	}

	public void setStatus(TrainStatus status) {
		this.status = status;
	}

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
	
}
