package com.backend.train_booking_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.train_booking_backend.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	int countByBookingId(Integer bookingId);
	
	<S extends Ticket> List<S> saveAll(Iterable<S> entities);
}
