package com.frontend.train_booking_frontend_customer.models;

import com.frontend.train_booking_frontend_customer.models.enums.Province;

import jakarta.validation.constraints.NotNull;

public class Route {
	private Integer id;
	
	private String routeName;
	
	@NotNull(message = "Nơi đi không được để trống")
	private Province departureLocation;
	
	@NotNull(message = "Nơi đến không được để trống")
	private Province destinationLocation;
	
	private Integer trainId;  	
	private Train train;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Province getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(Province departureLocation) {
		this.departureLocation = departureLocation;
	}

	public Province getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(Province destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public Integer getTrainId() {
		return trainId;
	}

	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	
	
}
