package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Schedule {
	private Integer id;
	
	@NotEmpty(message = "Tên chuyến đi không được để trống")
    @Size(min = 3, max = 100, message = "Tên chuyến đi phải có độ dài từ 3 đến 100 ký tự")
    private String scheduleName;
	
	@NotEmpty(message = "Ga đi không được để trống")
    @Size(min = 3, max = 100, message = "Ga đi phải có độ dài từ 3 đến 100 ký tự")
    private String departureStation;
    
	@NotEmpty(message = "Ga đến không được để trống")
    @Size(min = 3, max = 100, message = "Ga đến phải có độ dài từ 3 đến 100 ký tự")
    private String destinationStation;
	
	@NotNull(message = "Ngày khởi hành không được để trống")
	@Future(message = "Ngày khởi hành phải là một ngày trong tương lai")
	private LocalDateTime departureDate;

	@NotNull(message = "Ngày đến dự kiến không được để trống")
	@Future(message = "Ngày đến dự kiến phải là một ngày trong tương lai")
	private LocalDateTime estimateArrivalDate;


	@NotNull(message = "Mã tàu không được để trống")
    private Integer trainId;  	
	private Train train;
	
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
	public String getDepartureStation() {
		return departureStation;
	}
	public void setDepartureStation(String departureStation) {
		this.departureStation = departureStation;
	}
	public String getDestinationStation() {
		return destinationStation;
	}
	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}
	public LocalDateTime getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}
	public LocalDateTime getEstimateArrivalDate() {
		return estimateArrivalDate;
	}
	public void setEstimateArrivalDate(LocalDateTime estimateArrivalDate) {
		this.estimateArrivalDate = estimateArrivalDate;
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
