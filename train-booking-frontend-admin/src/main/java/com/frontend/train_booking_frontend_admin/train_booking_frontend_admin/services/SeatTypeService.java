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
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.SeatType;

import jakarta.servlet.http.HttpSession;

@Service
public class SeatTypeService {
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

	public List<SeatType> getAllSeatTypes() {
		try {
			setJwtToken();
			String url = apiUrl + "api/seattype";
			HttpEntity<SeatType> entity = new HttpEntity<>(headers);
			SeatType[] seattypes = restTemplate.exchange(url, HttpMethod.GET, entity, SeatType[].class).getBody();

			return Arrays.asList(seattypes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public SeatType getSeatTypeById(Integer id) {
		try {
			setJwtToken();
			String url = apiUrl + "api/seattype/id/" + id;
	        
	        HttpEntity<Void> entity = new HttpEntity<>(headers);
	        
	        return restTemplate.exchange(url, HttpMethod.GET, entity, SeatType.class).getBody();
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addSeatType(SeatType seattype) {
        try {
            setJwtToken();
            String url = apiUrl + "api/seattype";
            HttpEntity<SeatType> entity = new HttpEntity<>(seattype, headers);
            restTemplate.postForObject(url, entity, SeatType.class);
            return true;
        } catch (ResourceAccessException | HttpClientErrorException e) {
            e.printStackTrace();
            return false;
        }
    }

	public boolean updateSeatType(SeatType seattype) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/seattype/" + seattype.getId();
	        
	        HttpEntity<SeatType> entity = new HttpEntity<>(seattype, headers);
	        
	        restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	public boolean deleteSeatType(Integer id) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/seattype/" + id;
	        
	        HttpEntity<Void> entity = new HttpEntity<>(headers);
	        
	        restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    	}
	    }
}

	

	