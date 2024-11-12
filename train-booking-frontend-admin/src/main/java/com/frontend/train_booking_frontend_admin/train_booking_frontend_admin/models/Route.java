package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Route {
	private Integer id;
	
	@NotEmpty(message = "Tên lịch trình không được để trống")
    @Size(min = 3, max = 100, message = "Tên lịch trình phải có độ dài từ 3 đến 100 ký tự")
    private String routeName;

	@NotEmpty(message = "Nơi đi không được để trống")
    @Size(min = 3, max = 100, message = "Nơi đi phải có độ dài từ 3 đến 100 ký tự")
    private String departureLocation;
    
	@NotEmpty(message = "Nơi đến không được để trống")
    @Size(min = 3, max = 100, message = "Nơi đến phải có độ dài từ 3 đến 100 ký tự")
    private String destinationLocation;
    
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
	public String getDepartureLocation() {
		return departureLocation;
	}
	public void setDepartureLocation(String departureLocation) {
		this.departureLocation = departureLocation;
	}
	public String getDestinationLocation() {
		return destinationLocation;
	}
	public void setDestinationLocation(String destinationLocation) {
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
