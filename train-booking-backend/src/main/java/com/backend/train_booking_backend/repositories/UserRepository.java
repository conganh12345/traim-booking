package com.backend.train_booking_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.backend.train_booking_backend.models.AppUser;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
//	public AppUser findUserByUsername(String username);

	public AppUser findByEmailAndPassword(String email, String password);

//	public AppUser findByEmail(String email);
	public AppUser findUserByEmail(String email);

}
