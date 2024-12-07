package com.backend.train_booking_backend.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.train_booking_backend.models.Schedule;
import com.backend.train_booking_backend.models.enums.Province;
import com.backend.train_booking_backend.models.enums.ScheduleStatus;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
	@Query("SELECT s FROM Schedule s " +
		       "JOIN s.route r " +
		       "WHERE r.departureLocation = :departureLocation " +
		       "AND r.destinationLocation = :destinationLocation " +
		       "AND FUNCTION('DATE', s.departureDate) = :departureDate " + 
		       "AND s.status = :status")
		List<Schedule> findSchedulesByRouteAndDate(
		        @Param("departureLocation") Province departureLocation,
		        @Param("destinationLocation") Province destinationLocation,
		        @Param("departureDate") LocalDate departureDate,
		        @Param("status") ScheduleStatus status
		);
}
