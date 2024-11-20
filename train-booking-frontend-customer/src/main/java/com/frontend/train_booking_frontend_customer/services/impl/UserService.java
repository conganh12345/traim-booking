package com.frontend.train_booking_frontend_customer.services.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.frontend.train_booking_frontend_customer.models.User;
import com.frontend.train_booking_frontend_customer.services.IUserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService implements IUserService {
	@Value("${api.base.url}")
	private String apiUrl;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	RestTemplate restTemplate;
	
	private String jwtToken;
	
	private HttpHeaders headers;
	
	public void setJwtToken() {
		this.jwtToken = (String)session.getAttribute("token");
		this.headers = new HttpHeaders();
		this.headers.set("Authorization", "Bearer " + jwtToken);
	}

//	@Override
//	public List<User> getAllUsers() {
//
//		try {
//			// Get list user from API
//			User[] users = (User[]) restTemplate.getForObject(apiUrl + "api/user", User[].class);
//
//			return Arrays.asList(users);
//		} catch (ResourceAccessException e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}

	@Override
	public User userProfile() {
		try {
			String email = (String)session.getAttribute("loginEmail");
			setJwtToken();
			
			String url = apiUrl + "api/auth/findByEmail/" + email;
			HttpEntity<User> entity = new HttpEntity<>(headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, User.class).getBody();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean updateUser(User user) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/user/" + user.getId();
	        
	        HttpEntity<User> entity = new HttpEntity<>(user, headers);
	        
	        restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}