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
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Route;

import jakarta.servlet.http.HttpSession;

@Service
public class RouteService {
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

	public List<Route> getAllRoutes() {
		try {
			setJwtToken();
			String url = apiUrl + "api/route";
			HttpEntity<Route> entity = new HttpEntity<>(headers);
			Route[] routes = restTemplate.exchange(url, HttpMethod.GET, entity, Route[].class).getBody();

			return Arrays.asList(routes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Route getRouteById(Integer id) {
		try {
			setJwtToken();
			String url = apiUrl + "api/route/id/" + id;
	        
	        HttpEntity<Void> entity = new HttpEntity<>(headers);
	        
	        return restTemplate.exchange(url, HttpMethod.GET, entity, Route.class).getBody();
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addRoute(Route route) {
        try {
            setJwtToken();
            String url = apiUrl + "api/route";
            HttpEntity<Route> entity = new HttpEntity<>(route, headers);
            restTemplate.postForObject(url, entity, Route.class);
            return true;
        } catch (ResourceAccessException | HttpClientErrorException e) {
            e.printStackTrace();
            return false;
        }
    }

	public boolean updateRoute(Route route) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/route/" + route.getId();
	        
	        HttpEntity<Route> entity = new HttpEntity<>(route, headers);
	        
	        restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	public boolean deleteRoute(Integer id) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/route/" + id;
	        
	        HttpEntity<Void> entity = new HttpEntity<>(headers);
	        
	        restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    	}
	    }
}

	

	