package com.backend.train_booking_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.backend.train_booking_backend.models.AppUser;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
	public AppUser findUserByUsername(String username);
	
//	@Query("SELECT u FROM User u WHERE u.email = :email")
//	public List<AppUser> findByEmail(@Param("email") String email);
//	
    public AppUser findByEmailAndPassword(String email, String password);
    
    public AppUser findByEmail(String email);
    
}
