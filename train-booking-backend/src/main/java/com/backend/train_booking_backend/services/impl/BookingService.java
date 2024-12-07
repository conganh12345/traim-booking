package com.backend.train_booking_backend.services.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public Booking findById(int bookingId) {
		Optional<Booking> booking = bookingRepo.findById(bookingId);
		if (booking.isPresent()) {
			return booking.get();
		}
		return null;
	}

	public Map<String, Integer> getBookingStatistics() {
		Map<String, Integer> bookingStatistics = new HashMap<>();

		LocalDate endDate = LocalDate.now();
		LocalDate startDate = endDate.minusDays(14);

		LocalDateTime startDateTime = startDate.atStartOfDay();
		LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

		List<Object[]> stats = bookingRepo.getBookingStatistics(startDateTime, endDateTime);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for (Object[] stat : stats) {
			LocalDateTime dateTime = (LocalDateTime) stat[0];
			Long count = (Long) stat[1];
			bookingStatistics.put(dateTime.toLocalDate().format(formatter), count.intValue());
		}

	        return bookingStatistics;
	    }

		public Map<String, Long> getRevenueForLast15Days() {
			LocalDateTime endDate = LocalDateTime.now();
			LocalDateTime startDate = endDate.minusDays(14);
	
			List<Object[]> statistics = bookingRepo.getRevenueStatistics(startDate, endDate);
	
			Map<String, Long> revenueData = new HashMap<>();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
			for (Object[] row : statistics) {
				java.sql.Date sqlDate = (java.sql.Date) row[0];
				LocalDateTime date = sqlDate.toLocalDate().atStartOfDay(); 
				String formattedDate = date.format(formatter);
				
				BigDecimal revenue = (BigDecimal) row[1];
				revenueData.put(formattedDate, revenue.longValue());
			}
	
			return revenueData;
		}

		@Override
	public List<Booking> findByUserId(int userId) {
		List<Booking> booking = bookingRepo.findByUserId(userId);

		if (booking.isEmpty()) {
			return null;
		}
		return booking;
	}
	
}








