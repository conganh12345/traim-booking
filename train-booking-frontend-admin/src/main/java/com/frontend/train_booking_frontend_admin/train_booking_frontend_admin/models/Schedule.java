package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import java.time.LocalDateTime;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.ScheduleStatus;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Schedule {
	private Integer id;
	
	@NotEmpty(message = "Tên chuyến đi không được để trống")
    @Size(min = 3, max = 100, message = "Tên chuyến đi phải có độ dài từ 3 đến 100 ký tự")
    private String scheduleName;
	
	@NotNull(message = "Ngày khởi hành không được để trống")
	@Future(message = "Ngày khởi hành phải là một ngày trong tương lai")
	private LocalDateTime departureDate;

	@NotNull(message = "Ngày đến dự kiến không được để trống")
	@Future(message = "Ngày đến dự kiến phải là một ngày trong tương lai")
	private LocalDateTime estimateArrivalDate;
	
	@NotNull(message = "Trạng thái không được để trống")
    private ScheduleStatus status;

	@NotNull(message = "Hành trình không được để trống")
    private Integer routeId;  	
	private Route route;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getScheduleName() {
		return scheduleName;
	}
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}
	public LocalDateTime getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
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
	public LocalDateTime getEstimateArrivalDate() {
		return estimateArrivalDate;
	}
	public void setEstimateArrivalDate(LocalDateTime estimateArrivalDate) {
		this.estimateArrivalDate = estimateArrivalDate;
	}
	public ScheduleStatus getStatus() {
		return status;
	}
	public void setStatus(ScheduleStatus status) {
		this.status = status;
	}
}
