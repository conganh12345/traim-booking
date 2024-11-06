package com.backend.train_booking_backend.services;

import java.util.List;
import java.util.Optional;

import com.backend.train_booking_backend.models.AppUser;

public interface IUserService {
	List<AppUser> getAllUsers();

	AppUser getUser(Integer id);

	AppUser addUser(AppUser user);

	AppUser updateUser(Integer id, AppUser user);

//	AppUser findUserByUsername(String username);

	// List<User> deleteUser(Integer[] ids);

	AppUser findUserByEmail(String email);

	AppUser findUserByEmailAndPassword(String email, String password);

	Optional<AppUser> deleteUser(Integer id);

	// AppUser findByEmail(String email);

	AppUser save(AppUser user);
}
