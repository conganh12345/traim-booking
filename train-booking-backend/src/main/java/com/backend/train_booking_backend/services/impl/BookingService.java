package com.backend.train_booking_backend.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.train_booking_backend.models.Booking;
import com.backend.train_booking_backend.repositories.BookingRepository;
import com.backend.train_booking_backend.services.IBookingService;

@Service
public class BookingService implements IBookingService {

	@Autowired
	private BookingRepository bookingRepo;

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepo.findAll();
	}

	@Override
	public Booking getBooking(Integer id) {
		return bookingRepo.findById(id).get();
	}

	@Override
	@Transactional
	public Booking addBooking(Booking booking) {
		try {
			booking.setBookingTime(LocalDateTime.now());

			return bookingRepo.save(booking);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm đặt vé.", e);
		}
	}

	@Override
	@Transactional
	public Booking updateBooking(Integer id, Booking booking) {
		try {
			Optional<Booking> oldBookingOpt = bookingRepo.findById(id);
			if (oldBookingOpt.isPresent()) {
				booking.setId(id);
				booking.setBookingTime(LocalDateTime.now());
			}
			return bookingRepo.save(booking);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa đặt vé.", e);
		}
	}



	@Override
	@Transactional
	public boolean deleteBooking(Integer id) {
	    try {
	        if (bookingRepo.existsById(id)) {
	        	bookingRepo.deleteById(id);
	            return true;
	        } else {
	            System.out.println("Train with ID " + id + " not found.");
	            return false;
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Đã xảy ra lỗi khi xóa tàu.", e);
	    }
	}
}
