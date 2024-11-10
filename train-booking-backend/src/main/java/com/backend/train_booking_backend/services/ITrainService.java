package com.backend.train_booking_backend.services;

import java.util.List;

import com.backend.train_booking_backend.models.Train;

public interface ITrainService {
	List<Train> getAllTrains();

	Train getTrain(Integer id);

	Train addTrain(Train train);

	Train updateTrain(Integer id, Train train);

	int deleteTrain(Integer id);
}
