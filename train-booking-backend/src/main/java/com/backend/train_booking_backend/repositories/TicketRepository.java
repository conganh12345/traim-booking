package com.backend.train_booking_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.train_booking_backend.models.Seat;
import com.backend.train_booking_backend.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	int countByBookingId(Integer bookingId);
	
	<S extends Ticket> List<S> saveAll(Iterable<S> entities);
	
	 @Query("SELECT t.seat.id FROM Ticket t " +
	           "JOIN Booking b ON t.booking.id = b.id " +
	           "WHERE b.schedule.id = :scheduleId")
	    List<Integer> findBookedSeatIdsBySchedule(@Param("scheduleId") Integer scheduleId);
	 
	 List<Ticket> findByBookingId(int bookingId);
}
