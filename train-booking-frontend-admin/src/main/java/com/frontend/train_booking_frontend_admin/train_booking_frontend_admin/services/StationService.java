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
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Station;

import jakarta.servlet.http.HttpSession;

@Service
public class StationService {
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

	public List<Station> getAllStations() {
		try {
			setJwtToken();
			String url = apiUrl + "api/station";
			HttpEntity<Station> entity = new HttpEntity<>(headers);
			Station[] stations = restTemplate.exchange(url, HttpMethod.GET, entity, Station[].class).getBody();

			return Arrays.asList(stations);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Station getStationById(Integer id) {
		try {
			setJwtToken();
			String url = apiUrl + "api/station/id/" + id;
	        
	        HttpEntity<Void> entity = new HttpEntity<>(headers);
	        
	        return restTemplate.exchange(url, HttpMethod.GET, entity, Station.class).getBody();
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addStation(Station station) {
        try {
            setJwtToken();
            String url = apiUrl + "api/station";
            HttpEntity<Station> entity = new HttpEntity<>(station, headers);
            restTemplate.postForObject(url, entity, Station.class);
            return true;
        } catch (ResourceAccessException | HttpClientErrorException e) {
            e.printStackTrace();
            return false;
        }
    }

	public boolean updateStation(Station station) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/station/" + station.getId();
	        
	        HttpEntity<Station> entity = new HttpEntity<>(station, headers);
	        
	        restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	public boolean deleteStation(Integer id) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/station/" + id;
	        
	        HttpEntity<Void> entity = new HttpEntity<>(headers);
	        
	        restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    	}
	    }
}

	

	