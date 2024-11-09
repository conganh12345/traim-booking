package com.backend.train_booking_backend.services;

import java.util.List;

import com.backend.train_booking_backend.models.Coach;

public interface ICoachService {
	List<Coach> getAllCoachs();

	Coach getCoach(Integer id);

	Coach addCoach(Coach coach);

	Coach updateCoach(Integer id, Coach coach);

	int deleteCoach(Integer id);
}
