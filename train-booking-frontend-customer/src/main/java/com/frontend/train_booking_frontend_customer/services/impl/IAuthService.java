package com.frontend.train_booking_frontend_customer.services.impl;

import com.frontend.train_booking_frontend_customer.models.LoginRequest;
import com.frontend.train_booking_frontend_customer.models.User;

public interface IAuthService {
	User login(LoginRequest loginRequest);
	User signUp(User user);
}
