package com.backend.train_booking_backend.services.impl;

import java.time.LocalDateTime;
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
	@Transactional
	public Schedule addSchedule(Schedule schedule) {
		try {
			schedule.setDepartureDate(LocalDateTime.now());
			if (schedule.getEstimateArrivalDate() == null) {
				schedule.setEstimateArrivalDate(schedule.getDepartureDate().plusHours(5));
			}

			return scheduleRepo.save(schedule);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm lịch trình.", e);
		}
	}

	@Override
	@Transactional
	public Schedule updateSchedule(Integer id, Schedule schedule) {
		try {
			Optional<Schedule> oldScheduleOpt = scheduleRepo.findById(id);
			if (oldScheduleOpt.isPresent()) {
				schedule.setId(id);
				schedule.setDepartureDate(null);
				schedule.setDepartureDate(oldScheduleOpt.get().getDepartureDate());
				schedule.setEstimateArrivalDate(LocalDateTime.now());
			}
			return scheduleRepo.save(schedule);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa lịch trình.", e);
		}
	}



	@Override
	@Transactional
	public Optional<Schedule> deleteSchedule(Integer id) {
	    try {
	        Optional<Schedule> scheduleOpt = scheduleRepo.findById(id);
	        if (scheduleOpt.isPresent()) {
	        	Schedule schedule = scheduleOpt.get();
	            scheduleRepo.deleteById(id);
	            return Optional.of(schedule);
	        } else {
	            System.out.println("Schedule with ID " + id + " not found.");
	            return Optional.empty();
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Đã xảy ra lỗi khi xóa lịch trình.", e);
	    }
	}
}
