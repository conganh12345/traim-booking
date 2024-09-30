package com.backend.train_booking_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.train_booking_backend.models.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
	public Schedule findScheduleByScheduleName(String schedulename);
}
