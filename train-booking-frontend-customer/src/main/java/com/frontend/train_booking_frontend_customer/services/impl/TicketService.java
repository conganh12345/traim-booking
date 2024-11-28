package com.frontend.train_booking_frontend_customer.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.frontend.train_booking_frontend_customer.models.Ticket;
import com.frontend.train_booking_frontend_customer.services.ITicketService;

import jakarta.servlet.http.HttpSession;

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
}
