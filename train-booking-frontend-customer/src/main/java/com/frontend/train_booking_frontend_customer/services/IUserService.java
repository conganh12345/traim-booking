package com.frontend.train_booking_frontend_customer.services;

import java.util.List;

import com.frontend.train_booking_frontend_customer.models.User;

public interface IUserService {
	List<User> getAllUsers();
//	User getUserByEmail(String email);
//	User getUserByEmailPassword(String email, String password);
//	User signUp(User user);
	
	User userProfile();
}
