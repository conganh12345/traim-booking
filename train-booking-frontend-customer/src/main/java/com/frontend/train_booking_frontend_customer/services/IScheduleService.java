package com.frontend.train_booking_frontend_customer.services;

import java.time.LocalDate;
import java.util.List;

import com.frontend.train_booking_frontend_customer.models.Booking;
import com.frontend.train_booking_frontend_customer.models.Schedule;
import com.frontend.train_booking_frontend_customer.models.enums.Province;

public interface IScheduleService {
	public List<Schedule> GetScheduleByLocationAndTime(Province departureLocation, Province destinationLocation, LocalDate departureDate);
	
	public Schedule findById(int id);
}
