package com.backend.train_booking_backend.services;

import java.util.List;
import java.util.Optional;

import com.backend.train_booking_backend.models.User;

public interface IUserService {
	List<User> getAllUsers();

	User getUser(Integer id);

	User addUser(User user);

	User updateUser(Integer id, User user);

	User getUserByUsername(String username);

//	List<User> deleteUser(Integer[] ids);

	User findUserByEmail(String email);

	User findUserByEmailAndPassword(String email, String password);

	Optional<User> deleteUser(Integer id);
}
