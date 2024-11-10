package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.IService;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.LoginRequest;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.User;

public interface IAuthService {
	String login(LoginRequest loginRequest);
	User getUserByEmail(String email);
}
