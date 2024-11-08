package com.frontend.train_booking_frontend_customer.services.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@Service
public class UserService implements IUserService {
	@Value("${api.base.url}")
	private String apiUrl;

	@Override
	public List<User> getAllUsers() {
		// Call API to get all users
		RestTemplate restTemplate = new RestTemplate();

		try {
			// Get list user from API
			User[] users = (User[]) restTemplate.getForObject(apiUrl + "api/user", User[].class);

			return Arrays.asList(users);
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}

	}

//	@Override
//	public User getUserByEmail(String email) {
//		RestTemplate restTemplate = new RestTemplate();
//
//		try {
//			String url = apiUrl + "api/user/findByEmail/{email}";
//			Map<String, String> params = new HashMap<>();
//			params.put("email", email);
//			User user = restTemplate.getForObject(url, User.class, params);
//
//			return user;
//
//		} catch (HttpClientErrorException e) {
//			// Cannot to find any account
//			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
//				return null;
//			} else {
//				e.printStackTrace();
//				throw new RuntimeException("Failed to fetch user: " + e.getMessage());
//			}
//		}
//	}
//
//	@Override
//	public User getUserByEmailPassword(String email, String password) {
//		RestTemplate restTemplate = new RestTemplate();
//		try {
//			String url = apiUrl + "api/user/findByEmailAndPassword/{email}/{password}";
//			Map<String, String> params = new HashMap<>();
//			params.put("email", email);
//			params.put("password", password);
//
//			// Call API
//			User user = restTemplate.getForObject(url, User.class, params);
//
//			// check result
//			return user;
//		} catch (HttpClientErrorException e) {
//			// Cannot to find any account
//			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
//				return null;
//			} else {
//				e.printStackTrace();
//				return null;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	@Override
//	public User signUp(User user) {
//		RestTemplate restTemplate = new RestTemplate();
//
//		try {
//			String url = apiUrl + "api/user";
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
//
//			HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
//			ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, requestEntity, User.class);
//
//			if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
//				return responseEntity.getBody();
//			} else {
//				throw new RuntimeException("Failed to sign up: " + responseEntity.getStatusCode());
//			}
//		} catch (HttpClientErrorException e) {
//			e.printStackTrace();
//			throw new RuntimeException("Error during sign up: " + e.getMessage());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

//	@Value("${api.base.url}")
//    private String apiUrl;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @SuppressWarnings("unchecked")
//	private <T> T callApi(String url, HttpMethod method, Object request) {
//        try {
//            HttpEntity<Object> entity = new HttpEntity<>(request);
//            ResponseEntity<T> response = restTemplate.exchange(url, method, entity, (Class<T>) Object.class);
//            return response.getBody();
//        } catch (HttpClientErrorException e) {
//            if (e.getStatusCode().is4xxClientError()) {
//                return null; 
//            }
//            throw e; 
//        } catch (ResourceAccessException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        User[] users = callApi(apiUrl + "api/user", HttpMethod.GET, null);
//        return users != null ? Arrays.asList(users) : null;
//    }
//
//    @Override
//    public User getUserByEmail(String email) {
//        String url = apiUrl + "api/user/findByEmail/{email}";
//        Map<String, String> params = new HashMap<>();
//        params.put("email", email);
//        return callApi(url, HttpMethod.GET, params);
//    }
//
//    @Override
//    public User getUserByEmailPassword(String email, String password) {
//        String url = apiUrl + "api/user/findByEmailAndPassword/{email}/{password}";
//        Map<String, String> params = new HashMap<>();
//        params.put("email", email);
//        params.put("password", password);
//        return callApi(url, HttpMethod.GET, params);
//    }
//
//    @Override
//    public User signUp(User user) {
//        String url = apiUrl + "api/user";
//        return callApi(url, HttpMethod.POST, user);
//    }

}