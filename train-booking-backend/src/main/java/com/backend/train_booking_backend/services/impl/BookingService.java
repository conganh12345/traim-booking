package com.backend.train_booking_backend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.train_booking_backend.models.AppUser;
import com.backend.train_booking_backend.models.Booking;
import com.backend.train_booking_backend.repositories.BookingRepository;
import com.backend.train_booking_backend.repositories.TicketRepository;
import com.backend.train_booking_backend.services.IBookingService;

@Service
public class BookingService implements IBookingService {
	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private TicketRepository ticketRepo;

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepo.findAll();
	}

	@Override
	public Booking getBooking(Integer id) {
		return bookingRepo.findById(id).get();
	}

	@Override
	public Booking addBooking(Booking booking) {
		try {
			return bookingRepo.save(booking);
		} catch (Exception e) {
			 e.printStackTrace();
			throw new RuntimeException("Đã xảy ra lỗi khi thêm vé đặt.", e);
		}
	}

	@Override
	public Booking updateBooking(Integer id, Booking booking) {
		try {
			Optional<Booking> oldBookingOpt = bookingRepo.findById(id);
			if (oldBookingOpt.isPresent()) {
				booking.setId(id);
			}
			return bookingRepo.save(booking);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa vé đặt.", e);
		}
	}

	@Override
	@Transactional
	public int deleteBooking(Integer id) {
	    try {
	        if (bookingRepo.existsById(id)) {
	            int seatCount = ticketRepo.countByBookingId(id); 

	            if (seatCount > 0) {
	                System.out.println("Không thể xóa booking với ID " + id + " vì vẫn còn vé liên kết.");
	                return 0; 
	            }

	            bookingRepo.deleteById(id);

	            if (!bookingRepo.existsById(id)) {
	                System.out.println("Đã xóa booking với ID " + id);
	                return 1;  
	            } else {
	                System.out.println("Không thể xóa booking với ID " + id + " vì vẫn còn vé liên kết.");
	                return 0; 
	            }
	        } else {
	            System.out.println("Booking với ID " + id + " không tồn tại.");
	            return 2;  
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0; 
	    }
	}
	
	@Override
	public Booking findByCode(String code) {
		Booking booking = bookingRepo.findByCode(code);

	    if (booking != null) {
	        return booking;
	    }
	    return null; 
	}

}








