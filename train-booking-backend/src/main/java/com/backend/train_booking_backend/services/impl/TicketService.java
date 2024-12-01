package com.backend.train_booking_backend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.train_booking_backend.models.Seat;
import com.backend.train_booking_backend.models.Ticket;
import com.backend.train_booking_backend.repositories.SeatRepository;
import com.backend.train_booking_backend.repositories.TicketRepository;
import com.backend.train_booking_backend.services.ITicketService;

@Service
public class TicketService implements ITicketService {
	@Autowired
	private TicketRepository ticketRepo;
	
	@Autowired
	private SeatRepository seatRepo;

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
	public int deleteTicket(Integer id) {
		try {
	        if (ticketRepo.existsById(id)) {
	            int seatCount = seatRepo.countByTicketId(id); 

	            if (seatCount > 0) {
	                System.out.println("Không thể xóa vé với ID " + id + " vì vẫn còn ghế liên kết.");
	                return 0; 
	            }

	            ticketRepo.deleteById(id);

	            if (!ticketRepo.existsById(id)) {
	                System.out.println("Đã xóa vé với ID " + id);
	                return 1;  
	            } else {
	                System.out.println("Không thể xóa vé với ID " + id);
	                return 0; 
	            }
	        } else {
	            System.out.println("Vé với ID " + id + " không tồn tại.");
	            return 2;  
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0; 
	    }
	}

	@Override
	public List<Ticket> addTickets(List<Ticket> tickets) {
		try {
			return ticketRepo.saveAll(tickets);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm các vé.", e);
		}
	}
	
	@Override
	  public List<Integer> getBookedSeatsForSchedule(Integer scheduleId) {
        return ticketRepo.findBookedSeatIdsBySchedule(scheduleId);
    }
	
	@Override
	public List<Ticket> getTicketsByBookingId(int bookingId) {
        return ticketRepo.findByBookingId(bookingId);
    }
}
