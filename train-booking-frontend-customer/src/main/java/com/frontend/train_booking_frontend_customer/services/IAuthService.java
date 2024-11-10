package com.frontend.train_booking_frontend_customer.services;

import com.frontend.train_booking_frontend_customer.models.LoginRequest;
import com.frontend.train_booking_frontend_customer.models.User;

public interface IAuthService {
	String login(LoginRequest loginRequest);
	User signUp(User user);
	User getUserByEmail(String email);
}
