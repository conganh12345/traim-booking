package com.backend.train_booking_backend.services.impl;

import java.time.LocalDateTime;
//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backend.train_booking_backend.models.AppUser;
import com.backend.train_booking_backend.repositories.BookingRepository;
import com.backend.train_booking_backend.repositories.UserRepository;
import com.backend.train_booking_backend.services.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BookingRepository bookingRepo;

	@Override
	public List<AppUser> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public AppUser getUser(Integer id) {
		return userRepo.findById(id).get();
	}

	@Override
	@Transactional
	public AppUser addUser(AppUser user) {
		try {
			return userRepo.save(user);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm người dùng.", e);
		}
	}

	@Override
	@Transactional
	public AppUser updateUser(Integer id, AppUser user) {
		try {
			Optional<AppUser> oldUserOpt = userRepo.findById(id);
			if (oldUserOpt.isPresent()) {
				user.setId(id);
			}
			return userRepo.save(user);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa người dùng.", e);
		}
	}

//	@Override
//	public AppUser findUserByUsername(String username) {
//		return userRepo.findUserByUsername(username);
//	}

	@Override
	@Transactional
	public int deleteUser(Integer id) {
		try {
	        if (userRepo.existsById(id)) {
	            int seatCount = bookingRepo.countByUserId(id); 

	            if (seatCount > 0) {
	                System.out.println("Không thể xóa user với ID " + id + " vì vẫn còn đặt vé liên kết.");
	                return 0; 
	            }

	            userRepo.deleteById(id);

	            if (!userRepo.existsById(id)) {
	                System.out.println("Đã xóa user với ID " + id);
	                return 1;  
	            } else {
	                System.out.println("Không thể xóa user với ID " + id);
	                return 0; 
	            }
	        } else {
	            System.out.println("User với ID " + id + " không tồn tại.");
	            return 2;  
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0; 
	    }
	}

	// @Override
	// public AppUser findUserByEmail(String email) {
	// List<AppUser> users = userRepo.findByEmail(email);
	//
	// if (users != null && !users.isEmpty()) {
	// return users.get(0);
	// }
	//
	// return null;
	// }

	// @Override
	// public AppUser findByEmail(String email) {
	// AppUser user = userRepo.findByEmail(email);
	//
	// if (user != null) {
	// return user;
	// }
	//
	// return null;
	// }

	@Override
	public AppUser findUserByEmail(String email) {
	    AppUser user = userRepo.findUserByEmail(email);

	    if (user != null) {
	        return user;
	    }
	    
	    return null; 
	}
	
	@Override
	public AppUser findUserByEmailAndPassword(String email, String password) {
		AppUser user = userRepo.findByEmailAndPassword(email, password);
		if (user != null) {
			return user;
		}
		return null;
	}

	public AppUser save(AppUser user) {
		return userRepo.save(user);
	}

}
