package com.frontend.train_booking_frontend_customer.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.frontend.train_booking_frontend_customer.models.Coach;
import com.frontend.train_booking_frontend_customer.services.ICoachService;

import jakarta.servlet.http.HttpSession;

@Service
public class CoachService implements ICoachService{

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

	
   @Override
    public List<Coach> getCoachesByTrainId(int trainId) {
        String url = apiUrl + "api/coach/list/" + trainId;
        
        try {
            HttpEntity<Void> requestEntity = new HttpEntity<>(null);
            
            ResponseEntity<List<Coach>> response = restTemplate.exchange(
	                url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Coach>>() {});
	        return response.getBody();

        } catch (Exception e) {
            System.out.println("Lỗi khi gọi API: " + e.getMessage());
            return List.of();
        }

    }
}