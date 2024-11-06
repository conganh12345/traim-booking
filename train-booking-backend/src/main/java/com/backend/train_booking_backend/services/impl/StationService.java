package com.backend.train_booking_backend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.train_booking_backend.models.Station;
import com.backend.train_booking_backend.repositories.StationRepository;
import com.backend.train_booking_backend.services.IStationService;

@Service
public class StationService implements IStationService {
	@Autowired
	private StationRepository stationRepo;

	@Override
	public List<Station> getAllStations() {
		return stationRepo.findAll();
	}

	@Override
	public Station getStation(Integer id) {
		return stationRepo.findById(id).get();
	}

	@Override
	@Transactional
	public Station addStation(Station station) {
		try {
			return stationRepo.save(station);

		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm nhà ga.", e);
		}
	}

	@Override
	public Station updateStation(Integer id, Station station) {
		try {
			Optional<Station> oldStationOpt = stationRepo.findById(id);
			if (oldStationOpt.isPresent()) {
				station.setId(id);
			}
			return stationRepo.save(station);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa nhà ga.", e);
		}
	}



	@Override
	@Transactional
	public boolean deleteStation(Integer id) {
	    try {
	        if (stationRepo.existsById(id)) {
	        	stationRepo.deleteById(id);
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
