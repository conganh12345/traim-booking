package com.backend.train_booking_backend.services.impl;

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
	public boolean deleteBooking(Integer id) {
	    try {
	        if (bookingRepo.existsById(id)) {
	        	bookingRepo.deleteById(id);
	            return true;
	        } else {
	            System.out.println("Booking with ID " + id + " not found.");
	            return false;
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Đã xảy ra lỗi khi xóa vé đặt.", e);
	    }
	}
}








