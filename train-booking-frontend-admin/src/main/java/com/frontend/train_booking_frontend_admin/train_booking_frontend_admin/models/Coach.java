package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;


import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.CoachStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Coach {
	private Integer id;
	
	@NotEmpty(message = "Tên toa không được để trống")
    @Size(min = 3, max = 50, message = "Tên toa phải có độ dài từ 3 đến 50 ký tự")
    private String coachName;

	@NotEmpty(message = "Mô tả không được để trống")
    @Size(max = 200, message = "Mô tả không được dài quá 200 ký tự")
    private String description;
    
	@NotNull(message = "Trạng thái không được để trống")
	private CoachStatus status;  
	 
	@NotNull(message = "Toa không được để trống")
    private Integer trainId;  	
	private Train train;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CoachStatus getStatus() {
		return status;
	}
	public void setStatus(CoachStatus status) {
		this.status = status;
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
