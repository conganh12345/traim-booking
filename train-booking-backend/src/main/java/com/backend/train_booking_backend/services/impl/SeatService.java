package com.backend.train_booking_backend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.train_booking_backend.models.Seat;
import com.backend.train_booking_backend.repositories.SeatRepository;
import com.backend.train_booking_backend.services.ISeatService;

@Service
public class SeatService implements ISeatService {
	@Autowired
	private SeatRepository seatRepo;

	@Override
	public List<Seat> getAllSeats() {
		return seatRepo.findAll();
	}

	@Override
	public Seat getSeat(Integer id) {
		return seatRepo.findById(id).get();
	}

	@Override
	public Seat addSeat(Seat seat) {
		try {
			return seatRepo.save(seat);
		} catch (Exception e) {
			 e.printStackTrace();
			throw new RuntimeException("Đã xảy ra lỗi khi thêm ghế.", e);
		}
	}

	@Override
	public Seat updateSeat(Integer id, Seat seat) {
		try {
			Optional<Seat> oldSeatOpt = seatRepo.findById(id);
			if (oldSeatOpt.isPresent()) {
				seat.setId(id);
			}
			return seatRepo.save(seat);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa ghế.", e);
		}
	}

	@Override
	@Transactional
	public boolean deleteSeat(Integer id) {
	    try {
	        if (seatRepo.existsById(id)) {
	            seatRepo.deleteById(id);

	            if (seatRepo.existsById(id)) {
	                System.out.println("Không thể xóa ghế với ID " + id);
	                return false;
	            }
	            return true;
	        } else {
	            System.out.println("Ghế với ID " + id + " không tồn tại.");
	            return false;
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Đã xảy ra lỗi khi xóa ghế.", e);
	    }
	}
}








