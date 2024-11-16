package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services;

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
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.User;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
	@Value("${api.base.url}")
	private String apiUrl;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private String jwtToken;
	private HttpHeaders headers;
	
	public void setJwtToken() {
		this.jwtToken = (String)session.getAttribute("token");
		this.headers = new HttpHeaders();
		this.headers.set("Authorization", "Bearer " + jwtToken);
	}

	public List<User> getAllUsers() {
		try {
			setJwtToken();
			String url = apiUrl + "api/admin";
			HttpEntity<User> entity = new HttpEntity<>(headers);
			User[] users = restTemplate.exchange(url, HttpMethod.GET, entity, User[].class).getBody();

			return Arrays.asList(users);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addUser(User user) {
        try {
            setJwtToken();
            String url = apiUrl + "api/admin";
            HttpEntity<User> entity = new HttpEntity<>(user, headers);
            restTemplate.postForObject(url, entity, User.class);
            return true;
        } catch (ResourceAccessException | HttpClientErrorException e) {
            e.printStackTrace();
            return false;
        }
    }

	public User getUserById(Integer id) {
		try {
			setJwtToken();
			String url = apiUrl + "api/admin/id/" + id;
	        
	        HttpEntity<Void> entity = new HttpEntity<>(headers);
	        
	        return restTemplate.exchange(url, HttpMethod.GET, entity, User.class).getBody();
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateUser(User user) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/admin/" + user.getId();
	        
	        HttpEntity<User> entity = new HttpEntity<>(user, headers);
	        
	        restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	public boolean deleteUser(Integer id) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/admin/" + id;
	        
	        HttpEntity<Void> entity = new HttpEntity<>(headers);
	        
	        restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    	}
	    }
	
	
	public User userProfile() {
		try {
			String email = (String)session.getAttribute("loginEmail");
			HttpHeaders headers = new HttpHeaders();
			setJwtToken();
			headers.set("Authorization", "Bearer " + jwtToken);
			
			String url = apiUrl + "api/auth/findByEmail/" + email;
			HttpEntity<User> entity = new HttpEntity<>(headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, User.class).getBody();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

	public User getUserByEmail(String email) {
		RestTemplate restTemplate = new RestTemplate();

		try {
			String url = apiUrl + "api/user/findByEmail/{email}";
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
}