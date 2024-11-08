package com.backend.train_booking_backend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.train_booking_backend.models.Schedule;
import com.backend.train_booking_backend.repositories.ScheduleRepository;
import com.backend.train_booking_backend.services.IScheduleService;

@Service
public class ScheduleService implements IScheduleService {
	@Autowired
	private ScheduleRepository scheduleRepo;

	@Override
	public List<Schedule> getAllSchedules() {
		return scheduleRepo.findAll();
	}

	@Override
	public Schedule getSchedule(Integer id) {
		return scheduleRepo.findById(id).get();
	}

	@Override
	public Schedule addSchedule(Schedule schedule) {
		try {
			return scheduleRepo.save(schedule);
		} catch (Exception e) {
			 e.printStackTrace();
			throw new RuntimeException("Đã xảy ra lỗi khi thêm chuyến đi.", e);
		}
	}

	@Override
	public Schedule updateSchedule(Integer id, Schedule schedule) {
		try {
			Optional<Schedule> oldScheduleOpt = scheduleRepo.findById(id);
			if (oldScheduleOpt.isPresent()) {
				schedule.setId(id);
			}
			return scheduleRepo.save(schedule);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa chuyến đi.", e);
		}
	}

	@Override
	@Transactional
	public boolean deleteSchedule(Integer id) {
	    try {
	        if (scheduleRepo.existsById(id)) {
	        	scheduleRepo.deleteById(id);
	            return true;
	        } else {
	            System.out.println("Schedule with ID " + id + " not found.");
	            return false;
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Đã xảy ra lỗi khi xóa chuyến đi.", e);
	    }
	}
}








