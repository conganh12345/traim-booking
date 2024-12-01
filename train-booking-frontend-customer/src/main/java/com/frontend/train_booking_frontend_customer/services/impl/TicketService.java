package com.frontend.train_booking_frontend_customer.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.frontend.train_booking_frontend_customer.models.Seat;
import com.frontend.train_booking_frontend_customer.models.Ticket;
import com.frontend.train_booking_frontend_customer.services.ITicketService;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;


@Service
public class TicketService implements ITicketService{
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

	@Override
	public boolean addTickets(List<Ticket> tickets) {
		try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/ticket/add/tickets";
	        
	        HttpEntity<List<Ticket>> entity = new HttpEntity<>(tickets, headers);
	        
	        restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	@Override
	public List<Integer> getBookedSeats(Integer scheduleId) {
	    try {
	        String url = apiUrl + "api/ticket/booked-seats/" + scheduleId;
	        
	        ResponseEntity<List> response = restTemplate.exchange(
	            url,
	            HttpMethod.GET,
	            null, 
	            List.class
	        );
	        
	        return response.getBody();
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public List<Ticket> getTicketsByBookingId(int bookingId) {
        String url = apiUrl + "api/ticket/list/" + bookingId;
        
        try {
            HttpEntity<Void> requestEntity = new HttpEntity<>(null);
            
            ResponseEntity<List<Ticket>> response = restTemplate.exchange(
	                url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Ticket>>() {});
	        return response.getBody();

        } catch (Exception e) {
            System.out.println("Lỗi khi gọi API: " + e.getMessage());
            return List.of();
        }

    }
}
