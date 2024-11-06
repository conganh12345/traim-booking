package com.backend.train_booking_backend.services.impl;

import java.time.LocalDateTime;
//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.train_booking_backend.models.User;
import com.backend.train_booking_backend.repositories.UserRepository;
import com.backend.train_booking_backend.services.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getUser(Integer id) {
		return userRepo.findById(id).get();
	}

	@Override
	@Transactional
	public User addUser(User user) {
		try {
			user.setCreatedTime(LocalDateTime.now());

			return userRepo.save(user);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm người dùng.", e);
		}
	}

	@Override
	@Transactional
	public User updateUser(Integer id, User user) {
		try {
			Optional<User> oldUserOpt = userRepo.findById(id);
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
	public User getUserByUsername(String username) {
		return userRepo.findUserByUsername(username);
	}

	@Override
	@Transactional
	public Optional<User> deleteUser(Integer id) {
	    try {
	        Optional<User> userOpt = userRepo.findById(id);
	        if (userOpt.isPresent()) {
	            User user = userOpt.get();
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

	@Override
	public User findUserByEmail(String email) {
	    List<User> users = userRepo.findByEmail(email);

	    if (users != null && !users.isEmpty()) {
	        return users.get(0);
	    }

	    return null;
	}

	@Override
	public User findUserByEmailAndPassword(String email, String password) {
		User user = userRepo.findByEmailAndPassword(email, password);
		if(user != null) {
			return user;
		}
		return null;
	}


}
