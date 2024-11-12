package com.backend.train_booking_backend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.train_booking_backend.models.Coach;
import com.backend.train_booking_backend.repositories.CoachRepository;
import com.backend.train_booking_backend.repositories.SeatRepository;
import com.backend.train_booking_backend.services.ICoachService;

@Service
public class CoachService implements ICoachService {

	@Autowired
	private CoachRepository coachRepo;
	
	@Autowired
	private SeatRepository seatRepo;

	@Override
	public List<Coach> getAllCoachs() {
		return coachRepo.findAll();
	}

	@Override
	public Coach getCoach(Integer id) {
		return coachRepo.findById(id).get();
	}

	@Override
	public Coach addCoach(Coach coach) {
		try {
			return coachRepo.save(coach);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Đã xảy ra lỗi khi thêm toa.", e);
		}
	}

	@Override
	public Coach updateCoach(Integer id, Coach coach) {
		try {
			Optional<Coach> oldCoachOpt = coachRepo.findById(id);
			if (oldCoachOpt.isPresent()) {
				coach.setId(id);
			}
			return coachRepo.save(coach);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa toa.", e);
		}
	}

	@Override
	@Transactional
	public int deleteCoach(Integer id) {
		try {
	        if (coachRepo.existsById(id)) {
	            int seatCount = seatRepo.countByCoachId(id); 

	            if (seatCount > 0) {
	                System.out.println("Không thể xóa toa với ID " + id + " vì vẫn còn đặt vé liên kết.");
	                return 0; 
	            }

	            coachRepo.deleteById(id);

	            if (!coachRepo.existsById(id)) {
	                System.out.println("Đã xóa toa với ID " + id);
	                return 1;  
	            } else {
	                System.out.println("Không thể xóa toa với ID " + id);
	                return 0; 
	            }
	        } else {
	            System.out.println("Toa với ID " + id + " không tồn tại.");
	            return 2;  
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0; 
	    }
	}
}
