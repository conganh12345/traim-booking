package com.backend.train_booking_backend.services;

import java.util.List;
import java.util.Optional;

import com.backend.train_booking_backend.models.Schedule;

public interface IScheduleService {
	List<Schedule> getAllSchedules();

	Schedule getSchedule(Integer id);

	Schedule addSchedule(Schedule user);

	Schedule updateSchedule(Integer id, Schedule user);


	Optional<Schedule> deleteSchedule(Integer id);
}
