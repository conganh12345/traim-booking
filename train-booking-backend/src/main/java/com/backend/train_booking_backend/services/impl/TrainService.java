package com.backend.train_booking_backend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.train_booking_backend.models.Train;
import com.backend.train_booking_backend.repositories.BookingRepository;
import com.backend.train_booking_backend.repositories.RouteRepository;
import com.backend.train_booking_backend.repositories.ScheduleRepository;
import com.backend.train_booking_backend.repositories.TrainRepository;
import com.backend.train_booking_backend.services.ITrainService;

@Service
public class TrainService implements ITrainService {

	@Autowired
	private TrainRepository trainRepo;
	
	@Autowired
	private ScheduleRepository scheduleRepo;
	
	@Autowired
	private RouteRepository routeRepo;

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
	public int deleteTrain(Integer id) {
	    try {
	        if (trainRepo.existsById(id)) {
	            int routeCount = routeRepo.countByTrainId(id);


	            trainRepo.deleteById(id);

	            if (!trainRepo.existsById(id)) {
	                System.out.println("Đã xóa tàu với ID " + id);
	                return 1;  
	            } else {
	                System.out.println("Không thể xóa tàu với ID " + id);
	                return 0; 
	            }
	        } else {
	            System.out.println("Tàu với ID " + id + " không tồn tại.");
	            return 2;  
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0; 
	    }
	}

}
