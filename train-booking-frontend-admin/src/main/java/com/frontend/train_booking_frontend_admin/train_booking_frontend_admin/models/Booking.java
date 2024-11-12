package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import java.time.LocalDateTime;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.BookingStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public class Booking {
	private Integer id;
	
	@NotEmpty(message = "Giá không được để trống")
	@Min(value = 1000, message = "Gía phải lớn hơn hoặc bằng 1000")
    private String totalPrice;
	
	@NotEmpty(message = "Mã code không được để trống")
    private String code;
    
    @NotNull(message = "Thời gian đặt không được để trống")
    private LocalDateTime bookingTime;

	@NotEmpty(message = "Trạng thái không được để trống")
    private BookingStatus status;
	
	@NotNull(message = "Người dùng không được để trống")
    private Integer trainId;  	
	private Train train;
	
	@NotNull(message = "Chuyến đi không được để trống")
    private Integer scheduleId;  	
	private Schedule schedule;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public LocalDateTime getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}
	public BookingStatus getStatus() {
		return status;
	}
	public void setStatus(BookingStatus status) {
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
	public Integer getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
}
