package com.backend.train_booking_backend.services;

import java.time.LocalDate;
import java.util.List;

import com.backend.train_booking_backend.models.Schedule;
import com.backend.train_booking_backend.models.enums.Province;

public interface IScheduleService {
	List<Schedule> getAllSchedules();

	Schedule getSchedule(Integer id);

	Schedule addSchedule(Schedule schedule);

	Schedule updateSchedule(Integer id, Schedule schedule);

	int deleteSchedule(Integer id);
	
	List<Schedule> getSchedules(Province departureLocation, Province destinationLocation, LocalDate departureDate);
}
