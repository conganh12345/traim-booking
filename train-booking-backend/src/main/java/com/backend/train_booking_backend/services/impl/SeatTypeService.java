package com.backend.train_booking_backend.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backend.train_booking_backend.models.SeatType;
import com.backend.train_booking_backend.repositories.SeatTypeRepository;
import com.backend.train_booking_backend.services.ISeatTypeService;

@Service
public class SeatTypeService implements ISeatTypeService {
	@Autowired
	private SeatTypeRepository seatTypeRepo;

	@Override
	public List<SeatType> getAllSeatTypes() {
		return seatTypeRepo.findAll();
	}

	@Override
	public SeatType getSeatType(Integer id) {
		return seatTypeRepo.findById(id).get();
	}

	@Override
	@Transactional
	public SeatType addSeatType(SeatType seattype) {
		try {
			return seatTypeRepo.save(seattype);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm loại ghế.", e);
		}
	}

	@Override
	public SeatType updateSeatType(Integer id, SeatType seatType) {
		try {
			Optional<SeatType> oldSeatTypeOpt = seatTypeRepo.findById(id);
			if (oldSeatTypeOpt.isPresent()) {
				seatType.setId(id);
			}
			return seatTypeRepo.save(seatType);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa loại ghế.", e);
		}
	}

	@Override
	@Transactional
	public SeatType getSeatTypeBySeatTypename(String seatTypeName) {
		return seatTypeRepo.findSeatTypeBySeatTypeName(seatTypeName);
	}

	@Override
	public List<SeatType> deleteSeatType(Integer[] ids) {
		List<SeatType> seatTypeDeletes = new ArrayList<>();
		try {
			for (Integer id : ids) {
				Optional<SeatType> seattypeOpt = seatTypeRepo.findById(id);
				if (seattypeOpt.isPresent()) {
					SeatType seattype = seattypeOpt.get();
					seatTypeDeletes.add(seattype);
					seatTypeRepo.deleteById(id);
				} else {
					System.out.println("SeatType with ID " + id + " not found.");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi xóa loại ghế.", e);
		}
		return seatTypeDeletes;
	}
}
