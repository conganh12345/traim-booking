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
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Schedule;

import jakarta.servlet.http.HttpSession;

@Service
public class ScheduleService {
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

	public List<Schedule> getAllSchedules() {
		try {
			setJwtToken();
			String url = apiUrl + "api/schedule";
			HttpEntity<Schedule> entity = new HttpEntity<>(headers);
			Schedule[] schedules = restTemplate.exchange(url, HttpMethod.GET, entity, Schedule[].class).getBody();

			return Arrays.asList(schedules);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Schedule getScheduleById(Integer id) {
		try {
			setJwtToken();
			String url = apiUrl + "api/schedule/id/" + id;
	        
	        HttpEntity<Void> entity = new HttpEntity<>(headers);
	        
	        return restTemplate.exchange(url, HttpMethod.GET, entity, Schedule.class).getBody();
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addSchedule(Schedule schedule) {
        try {
            setJwtToken();
            String url = apiUrl + "api/schedule";
            HttpEntity<Schedule> entity = new HttpEntity<>(schedule, headers);
            restTemplate.postForObject(url, entity, Schedule.class);
            return true;
        } catch (ResourceAccessException | HttpClientErrorException e) {
            e.printStackTrace();
            return false;
        }
    }

	public boolean updateSchedule(Schedule schedule) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/schedule/" + schedule.getId();
	        
	        HttpEntity<Schedule> entity = new HttpEntity<>(schedule, headers);
	        
	        restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	public boolean deleteSchedule(Integer id) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/schedule/" + id;
	        
	        HttpEntity<Void> entity = new HttpEntity<>(headers);
	        
	        restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    	}
	    }
	
}

	

	