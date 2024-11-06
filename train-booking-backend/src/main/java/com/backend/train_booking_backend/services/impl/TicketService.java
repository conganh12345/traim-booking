package com.backend.train_booking_backend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.train_booking_backend.models.Ticket;
import com.backend.train_booking_backend.repositories.TicketRepository;
import com.backend.train_booking_backend.services.ITicketService;

@Service
public class TicketService implements ITicketService {
	@Autowired
	private TicketRepository ticketRepo;

	@Override
	public List<Ticket> getAllTickets() {
		return ticketRepo.findAll();
	}

	@Override
	public Ticket getTicket(Integer id) {
		return ticketRepo.findById(id).get();
	}

	@Override
	public Ticket addTicket(Ticket ticket) {
		try {
			return ticketRepo.save(ticket);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm vé.", e);
		}
	}

	@Override
	public Ticket updateTicket(Integer id, Ticket ticket) {
		try {
			Optional<Ticket> oldTicketOpt = ticketRepo.findById(id);
			if (oldTicketOpt.isPresent()) {
				ticket.setId(id);
			}
			return ticketRepo.save(ticket);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa vé.", e);
		}
	}



	@Override
	@Transactional
	public boolean deleteTicket(Integer id) {
	    try {
	        if (ticketRepo.existsById(id)) {
	        	ticketRepo.deleteById(id);
	            return true;
	        } else {
	            System.out.println("Ticket with ID " + id + " not found.");
	            return false;
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Đã xảy ra lỗi khi xóa tàu.", e);
	    }
	}
}
