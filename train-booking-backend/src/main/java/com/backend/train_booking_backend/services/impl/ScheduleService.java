package com.backend.train_booking_backend.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.train_booking_backend.models.Schedule;
import com.backend.train_booking_backend.models.enums.Province;
import com.backend.train_booking_backend.repositories.BookingRepository;
import com.backend.train_booking_backend.repositories.ScheduleRepository;
import com.backend.train_booking_backend.services.IScheduleService;

@Service
public class ScheduleService implements IScheduleService {
	@Autowired
	private ScheduleRepository scheduleRepo;
	
	@Autowired
	private BookingRepository bookingRepo;

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
	public int deleteSchedule(Integer id) {
		try {
	        if (scheduleRepo.existsById(id)) {
	            int seatCount = bookingRepo.countByScheduleId(id); 

	            if (seatCount > 0) {
	                System.out.println("Không thể xóa chuyến đi với ID " + id + " vì vẫn còn đặt vé liên kết.");
	                return 0; 
	            }

	            scheduleRepo.deleteById(id);

	            if (!scheduleRepo.existsById(id)) {
	                System.out.println("Đã xóa chuyến đi với ID " + id);
	                return 1;  
	            } else {
	                System.out.println("Không thể xóa chuyến đi với ID " + id);
	                return 0; 
	            }
	        } else {
	            System.out.println("Chuyến đi với ID " + id + " không tồn tại.");
	            return 2;  
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0; 
	    }
	}

	@Override
	public List<Schedule> getSchedules(Province departureLocation, Province destinationLocation, LocalDate departureDate) {
		try {
	        return scheduleRepo.findSchedulesByRouteAndDate(departureLocation, destinationLocation, departureDate);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ArrayList<>();
	    }
	}
}








