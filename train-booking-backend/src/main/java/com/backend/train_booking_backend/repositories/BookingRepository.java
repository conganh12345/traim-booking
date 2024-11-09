package com.backend.train_booking_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.train_booking_backend.models.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	int countByUserId(Integer userId);
}
