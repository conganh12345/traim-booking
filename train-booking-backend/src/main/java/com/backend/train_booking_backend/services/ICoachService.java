package com.backend.train_booking_backend.services;

import java.util.List;
import java.util.Optional;

import com.backend.train_booking_backend.models.Coach;

public interface ICoachService {
	List<Coach> getAllCoachs();

	Coach getCoach(Integer id);

	Coach addCoach(Coach coach);

	Coach updateCoach(Integer id, Coach coach);

	Coach getCoachByCoachName(String coachname);

	boolean deleteCoach(Integer id);
}
