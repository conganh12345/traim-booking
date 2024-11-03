package com.backend.train_booking_backend.services.impl;

import java.time.LocalDateTime;
//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backend.train_booking_backend.models.AppUser;
import com.backend.train_booking_backend.repositories.UserRepository;
import com.backend.train_booking_backend.services.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepo;

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
			user.setCreatedTime(LocalDateTime.now());

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
				user.setCreatedTime(oldUserOpt.get().getCreatedTime());
				user.setUpdatedTime(LocalDateTime.now());
			}
			return userRepo.save(user);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa người dùng.", e);
		}
	}

	@Override
	public AppUser findUserByUsername(String username) {
		return userRepo.findUserByUsername(username);
	}

	@Override
	@Transactional
	public Optional<AppUser> deleteUser(Integer id) {
	    try {
	        Optional<AppUser> userOpt = userRepo.findById(id);
	        if (userOpt.isPresent()) {
	            AppUser user = userOpt.get();
	            userRepo.deleteById(id);
	            return Optional.of(user); 
	        } else {
	            System.out.println("User with ID " + id + " not found.");
	            return Optional.empty();
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Đã xảy ra lỗi khi xóa người dùng.", e);
	    }
	}
	
//	@Override
//	public AppUser findUserByEmail(String email) {
//	    List<AppUser> users = userRepo.findByEmail(email);
//
//	    if (users != null && !users.isEmpty()) {
//	        return users.get(0);
//	    }
//	    
//	    return null; 
//	}
	
//	@Override
//	public AppUser findByEmail(String email) {
//	    AppUser user = userRepo.findByEmail(email);
//
//	    if (user != null) {
//	        return user;
//	    }
//	    
//	    return null; 
//	}

	@Override
	public AppUser findUserByEmailAndPassword(String email, String password) {
		AppUser user = userRepo.findByEmailAndPassword(email, password);
		if(user != null) {
			return user;
		}
		return null;
	}
	
	public AppUser save(AppUser user) {
		return userRepo.save(user);
	}
	
}
