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
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Ticket;

import jakarta.servlet.http.HttpSession;

@Service
public class TicketService {
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

	public List<Ticket> getAllTickets() {
		try {
			setJwtToken();
			String url = apiUrl + "api/ticket";
			HttpEntity<Ticket> entity = new HttpEntity<>(headers);
			Ticket[] tickets = restTemplate.exchange(url, HttpMethod.GET, entity, Ticket[].class).getBody();

			return Arrays.asList(tickets);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Ticket getTicketById(Integer id) {
		try {
			setJwtToken();
			String url = apiUrl + "api/ticket/id/" + id;
	        
	        HttpEntity<Void> entity = new HttpEntity<>(headers);
	        
	        return restTemplate.exchange(url, HttpMethod.GET, entity, Ticket.class).getBody();
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addTicket(Ticket ticket) {
        try {
            setJwtToken();
            String url = apiUrl + "api/ticket";
            HttpEntity<Ticket> entity = new HttpEntity<>(ticket, headers);
            restTemplate.postForObject(url, entity, Ticket.class);
            return true;
        } catch (ResourceAccessException | HttpClientErrorException e) {
            e.printStackTrace();
            return false;
        }
    }

	public boolean updateTicket(Ticket ticket) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/ticket/" + ticket.getId();
	        
	        HttpEntity<Ticket> entity = new HttpEntity<>(ticket, headers);
	        
	        restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	public boolean deleteTicket(Integer id) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/ticket/" + id;
	        
	        HttpEntity<Void> entity = new HttpEntity<>(headers);
	        
	        restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    	}
	    }
}

	

	