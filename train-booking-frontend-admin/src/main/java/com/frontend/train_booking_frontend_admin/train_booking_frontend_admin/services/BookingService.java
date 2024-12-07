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
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Booking;

import jakarta.servlet.http.HttpSession;

@Service
public class BookingService {
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

	public List<Booking> getAllBookings() {
		try {
			setJwtToken();
			String url = apiUrl + "api/booking";
			HttpEntity<Booking> entity = new HttpEntity<>(headers);
			Booking[] bookings = restTemplate.exchange(url, HttpMethod.GET, entity, Booking[].class).getBody();

			return Arrays.asList(bookings);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Booking getBookingById(Integer id) {
		try {
			setJwtToken();
			String url = apiUrl + "api/booking/id/" + id;
	        
	        HttpEntity<Void> entity = new HttpEntity<>(headers);
	        
	        return restTemplate.exchange(url, HttpMethod.GET, entity, Booking.class).getBody();
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateBooking(Booking booking) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/booking/" + booking.getId();
	        
	        HttpEntity<Booking> entity = new HttpEntity<>(booking, headers);
	        
	        restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	 public Map<String, Integer> getBookingStatistics() {
	        try {
	            setJwtToken();
	            String url = apiUrl + "api/booking/statistics";  

	            HttpEntity<Void> entity = new HttpEntity<>(headers);
	            return restTemplate.exchange(url, HttpMethod.GET, entity, Map.class).getBody();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;  
	        }
	    }
	 
	 public Map<String, Integer> getBookingRevenue() {
	        try {
	            setJwtToken();
	            String url = apiUrl + "api/booking/revenue";  

	            HttpEntity<Void> entity = new HttpEntity<>(headers);
	            return restTemplate.exchange(url, HttpMethod.GET, entity, Map.class).getBody();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;  
	        }
	    }
}

	

	