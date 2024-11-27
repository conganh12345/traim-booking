package com.frontend.train_booking_frontend_customer.services.impl;

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

import com.frontend.train_booking_frontend_customer.models.Seat;
import com.frontend.train_booking_frontend_customer.services.ISeatService;

import jakarta.servlet.http.HttpSession;

@Service
public class SeatService implements ISeatService{
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
