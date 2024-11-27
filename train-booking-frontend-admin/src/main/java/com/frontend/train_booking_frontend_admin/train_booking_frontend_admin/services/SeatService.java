package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Seat;

import jakarta.servlet.http.HttpSession;

@Service
public class SeatService {
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

	public List<Seat> getAllSeats() {
		try {
			setJwtToken();
			String url = apiUrl + "api/seat";
			HttpEntity<Seat> entity = new HttpEntity<>(headers);
			Seat[] seats = restTemplate.exchange(url, HttpMethod.GET, entity, Seat[].class).getBody();

			return Arrays.asList(seats);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Seat getSeatById(Integer id) {
		try {
			setJwtToken();
			String url = apiUrl + "api/seat/id/" + id;
	        
	        HttpEntity<Void> entity = new HttpEntity<>(headers);
	        
	        return restTemplate.exchange(url, HttpMethod.GET, entity, Seat.class).getBody();
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addSeat(Seat seat) {
        try {
            setJwtToken();
            String url = apiUrl + "api/seat";
            HttpEntity<Seat> entity = new HttpEntity<>(seat, headers);
            restTemplate.postForObject(url, entity, Seat.class);
            return true;
        } catch (ResourceAccessException | HttpClientErrorException e) {
            e.printStackTrace();
            return false;
        }
    }

	public boolean updateSeat(Seat seat) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/seat/" + seat.getId();
	        
	        HttpEntity<Seat> entity = new HttpEntity<>(seat, headers);
	        
	        restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	public boolean deleteSeat(Integer id) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/seat/" + id;
	        
	        HttpEntity<Void> entity = new HttpEntity<>(headers);
	        
	        restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    	}
	    }
	
	  public List<Seat> getSeatesByCoachId(int coachId) {
	        String url = apiUrl + "api/seat/list/" + coachId;
	        
	        try {
	            HttpEntity<Void> requestEntity = new HttpEntity<>(null);
	            
	            ResponseEntity<List<Seat>> response = restTemplate.exchange(
		                url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Seat>>() {});
		        return response.getBody();

	        } catch (Exception e) {
	            System.out.println("Lỗi khi gọi API: " + e.getMessage());
	            return List.of();
	        }

	    }
}
