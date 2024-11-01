package com.backend.train_booking_backend.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.management.RuntimeErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backend.train_booking_backend.models.Coach;
import com.backend.train_booking_backend.repositories.CoachRepository;
import com.backend.train_booking_backend.services.ICoachService;

@Service
public class CoachService implements ICoachService {

	@Autowired
	private CoachRepository coachRepo;

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
	public Coach getCoachByCoachName(String coachname) {
		return coachRepo.findCoachByCoachName(coachname);
	}
	
	@Override
	@Transactional
	public boolean deleteCoach(Integer id) {
	    try {
	        if (coachRepo.existsById(id)) {
	        	coachRepo.deleteById(id);
	            return true; 
	        } else {
	            System.out.println("Coach with ID " + id + " not found.");
	            return false;
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Đã xảy ra lỗi khi xóa toa.", e);
	    }
	}
}
