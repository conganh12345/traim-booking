package com.backend.train_booking_backend.services;

import java.util.List;

import com.backend.train_booking_backend.models.Schedule;

public interface IScheduleService {
	List<Schedule> getAllSchedules();

	Schedule getSchedule(Integer id);

	Schedule addSchedule(Schedule schedule);

	Schedule updateSchedule(Integer id, Schedule schedule);

	int deleteSchedule(Integer id);
}
