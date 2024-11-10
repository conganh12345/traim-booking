package com.backend.train_booking_backend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.train_booking_backend.models.SeatType;
import com.backend.train_booking_backend.repositories.SeatRepository;
import com.backend.train_booking_backend.repositories.SeatTypeRepository;
import com.backend.train_booking_backend.services.ISeatTypeService;

@Service
public class SeatTypeService implements ISeatTypeService {
	@Autowired
	private SeatTypeRepository seattypeRepo;
	
	@Autowired
	private SeatRepository seatRepo;

	@Override
	public List<SeatType> getAllSeatTypes() {
		return seattypeRepo.findAll();
	}

	@Override
	public SeatType getSeatType(Integer id) {
		return seattypeRepo.findById(id).get();
	}

	@Override
	public SeatType addSeatType(SeatType seattype) {
		try {
			return seattypeRepo.save(seattype);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Đã xảy ra lỗi khi thêm lịch trình.", e);
		}
	}

	@Override
	public SeatType updateSeatType(Integer id, SeatType seattype) {
		try {
			Optional<SeatType> oldSeatTypeOpt = seattypeRepo.findById(id);
			if (oldSeatTypeOpt.isPresent()) {
				seattype.setId(id);
			}
			return seattypeRepo.save(seattype);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa lịch trình.", e);
		}
	}

	@Override
	@Transactional
	public int deleteSeatType(Integer id) {
		try {
	        if (seattypeRepo.existsById(id)) {
	            int seatCount = seatRepo.countBySeatTypeId(id); 

	            if (seatCount > 0) {
	                System.out.println("Không thể xóa loại ghế với ID " + id + " vì vẫn còn ghế liên kết.");
	                return 0; 
	            }

	            seattypeRepo.deleteById(id);

	            if (!seattypeRepo.existsById(id)) {
	                System.out.println("Đã xóa  loại ghế  với ID " + id);
	                return 1;  
	            } else {
	                System.out.println("Không thể xóa  loại ghế  với ID " + id);
	                return 0; 
	            }
	        } else {
	            System.out.println("Loại ghế  với ID " + id + " không tồn tại.");
	            return 2;  
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0; 
	    }
	}
}
