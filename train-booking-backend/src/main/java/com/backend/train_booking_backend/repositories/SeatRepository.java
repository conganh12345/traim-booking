package com.backend.train_booking_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.train_booking_backend.models.Coach;
import com.backend.train_booking_backend.models.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
	int countByCoachId(Integer coachId);
	int countBySeatTypeId(Integer seatTypeId);
	int countByTicketId(Integer ticketId);
	
	List<Seat> findByCoachId(int coachId);
}
