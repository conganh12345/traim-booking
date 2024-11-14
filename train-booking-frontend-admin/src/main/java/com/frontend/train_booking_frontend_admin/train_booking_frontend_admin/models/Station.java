package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Station {
	private Integer id;
	
	@NotEmpty(message = "Tên ga không được để trống")
    @Size(min = 3, max = 100, message = "Tên ga phải có độ dài từ 3 đến 100 ký tự")
    private String stationName;
    
    @NotNull(message = "Lịch trình không được để trống")
    private Integer routeId;  	
	private Route route;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	
}
