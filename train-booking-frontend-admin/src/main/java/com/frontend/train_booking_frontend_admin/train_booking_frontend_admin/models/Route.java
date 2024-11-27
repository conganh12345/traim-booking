package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.Province;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Route {
	private Integer id;
	
	@NotEmpty(message = "Tên lịch trình không được để trống")
    @Size(min = 3, max = 100, message = "Tên lịch trình phải có độ dài từ 3 đến 100 ký tự")
    private String routeName;
	
	private double price;

	@NotNull(message = "Nơi đi không được để trống")
    private Province departureLocation;
    
	@NotNull(message = "Nơi đến không được để trống")
    private Province destinationLocation;
    
    @NotNull(message = "Tàu không được để trống")
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
