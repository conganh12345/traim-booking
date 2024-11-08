package com.frontend.train_booking_frontend_customer.services;

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

import com.frontend.train_booking_frontend_customer.models.LoginRequest;
import com.frontend.train_booking_frontend_customer.models.User;
import com.frontend.train_booking_frontend_customer.services.impl.IAuthService;

@Service
public class AuthService implements IAuthService{
	@Value("${api.base.url}")
	private String apiUrl;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public User login(LoginRequest loginRequest) {
		try {
			String url = apiUrl + "api/auth/login";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<LoginRequest> resquestEntity = new HttpEntity<>(loginRequest, headers);
			ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, resquestEntity, User.class);
			
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
	public User signUp(User user) {
		try {
			String url = apiUrl + "api/auth/register";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
			ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, requestEntity, User.class);

			if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
				return responseEntity.getBody();
			} else {
				throw new RuntimeException("Failed to sign up: " + responseEntity.getStatusCode());
			}
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			throw new RuntimeException("Error during sign up: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}

