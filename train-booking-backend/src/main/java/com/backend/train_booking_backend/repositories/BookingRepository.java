package com.backend.train_booking_backend.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.train_booking_backend.models.AppUser;
import com.backend.train_booking_backend.models.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	int countByUserId(Integer userId);
	int countByScheduleId(Integer schduleId);
	public Booking findByCode(String code);
	
	@Query("SELECT b.bookingTime, COUNT(b) FROM Booking b WHERE b.bookingTime BETWEEN :startDate AND :endDate GROUP BY b.bookingTime ORDER BY b.bookingTime")
	List<Object[]> getBookingStatistics(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

	@Query("SELECT DATE(b.bookingTime) AS bookingDate, SUM(b.totalPrice) AS totalRevenue " +
	       "FROM Booking b " +
	       "WHERE b.bookingTime BETWEEN :startDate AND :endDate " +
	       "GROUP BY DATE(b.bookingTime) " +
	       "ORDER BY DATE(b.bookingTime)")
	List<Object[]> getRevenueStatistics(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
