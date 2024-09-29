package com.backend.train_booking_backend.services;

import java.util.List;
import com.backend.train_booking_backend.models.User;

public interface IUserService {
	List<User> getAllUsers();

	User getUser(Integer id);

	User addUser(User user);

	User updateUser(Integer id, User user);

	User getUserByUsername(String username);

	List<User> deleteUser(Integer[] ids);
}