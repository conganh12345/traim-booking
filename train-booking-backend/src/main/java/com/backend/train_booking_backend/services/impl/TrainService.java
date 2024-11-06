package com.backend.train_booking_backend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.train_booking_backend.models.Train;
import com.backend.train_booking_backend.repositories.TrainRepository;
import com.backend.train_booking_backend.services.ITrainService;

@Service
public class TrainService implements ITrainService {

	@Autowired
	private TrainRepository trainRepo;

	@Override
	public List<Train> getAllTrains() {
		return trainRepo.findAll();
	}

	@Override
	public Train getTrain(Integer id) {
		return trainRepo.findById(id).get();
	}

	@Override
	public Train addTrain(Train train) {
		try {
			return trainRepo.save(train);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm tàu.", e);
		}
	}

	@Override
	public Train updateTrain(Integer id, Train train) {
		try {
			Optional<Train> oldTrainOpt = trainRepo.findById(id);
			if (oldTrainOpt.isPresent()) {
				train.setId(id);
			}
			return trainRepo.save(train);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa tàu.", e);
		}
	}



	@Override
	@Transactional
	public boolean deleteTrain(Integer id) {
	    try {
	        if (trainRepo.existsById(id)) {
	            trainRepo.deleteById(id);
	            return true;
	        } else {
	            System.out.println("Train with ID " + id + " not found.");
	            return false;
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Đã xảy ra lỗi khi xóa tàu.", e);
	    }
	}
}
