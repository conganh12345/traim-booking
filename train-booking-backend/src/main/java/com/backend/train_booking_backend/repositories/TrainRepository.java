package com.backend.train_booking_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.train_booking_backend.models.Train;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {
	public Train findTrainByTrainname(String trainname);
}
