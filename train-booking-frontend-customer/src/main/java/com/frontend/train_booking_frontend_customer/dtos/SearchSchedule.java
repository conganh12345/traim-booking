package com.frontend.train_booking_frontend_customer.dtos;

import java.time.LocalDate;

import com.frontend.train_booking_frontend_customer.models.enums.Province;

import jakarta.validation.constraints.NotNull;

public class SearchSchedule {
	@NotNull(message = "Nơi đi không được để trống")
	private Province departureLocation;
	
	@NotNull(message = "Nơi đến không được để trống")
	private Province destinationLocation;
	
	@NotNull(message = "Thời gian không được để trống")
	private LocalDate departureDate;

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

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}
	
	
}
