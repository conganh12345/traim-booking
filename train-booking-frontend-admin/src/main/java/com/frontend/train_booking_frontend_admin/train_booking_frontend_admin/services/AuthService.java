package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.LoginRequest;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.User;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.IService.IAuthService;


@Service
public class AuthService implements IAuthService{
	@Value("${api.base.url}")
	private String apiUrl;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public String login(LoginRequest loginRequest) {
		try {
			String url = apiUrl + "api/auth/generateToken";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<LoginRequest> resquestEntity = new HttpEntity<>(loginRequest, headers);
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, resquestEntity, String.class);
			
			return responseEntity.getBody();
			
		}catch(HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				return null;
			} else {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	@Override
	public User getUserByEmail(String email) {
		RestTemplate restTemplate = new RestTemplate();

		try {
			String url = apiUrl + "api/auth/findByEmail/{email}";
			Map<String, String> params = new HashMap<>();
			params.put("email", email);
			User user = restTemplate.getForObject(url, User.class, params);

			return user;

		} catch (HttpClientErrorException e) {
			// Cannot to find any account
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				return null;
			} else {
				e.printStackTrace();
				throw new RuntimeException("Failed to fetch user: " + e.getMessage());
			}
		}
	}

	
}
