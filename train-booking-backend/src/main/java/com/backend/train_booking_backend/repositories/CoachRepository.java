package com.backend.train_booking_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.backend.train_booking_backend.models.Coach;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Integer> {
	int countByTrainId(int trainId);
	
	List<Coach> findByTrainId(int trainId);
}

