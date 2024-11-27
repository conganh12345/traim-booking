package com.frontend.train_booking_frontend_customer.services.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.frontend.train_booking_frontend_customer.models.Schedule;
import com.frontend.train_booking_frontend_customer.models.enums.Province;
import com.frontend.train_booking_frontend_customer.services.IScheduleService;

import jakarta.servlet.http.HttpSession;

@Service
public class ScheduleService implements IScheduleService {

	@Value("${api.base.url}")
	private String apiUrl;
	
//	@Autowired
//	private HttpSession session;
	
	@Autowired
	private RestTemplate restTemplate;
	
//	private String jwtToken;
//	
//	private HttpHeaders headers;
	
//	public void setJwtToken() {
//		this.jwtToken = (String) session.getAttribute("token");
//		this.headers = new HttpHeaders();
//		this.headers.set("Authorization", "Bearer " + jwtToken);
//	}
	
	@Override
	public List<Schedule> GetScheduleByLocationAndTime(Province departureLocation, Province destinationLocation, LocalDate departureDate) {
	    try {
	        // Xây dựng URL với tham số query
	        String url = apiUrl + "api/schedule/search?" +
	                     "departureLocation=" + departureLocation.name() + "&" +
	                     "destinationLocation=" + destinationLocation.name() + "&" +
	                     "departureDate=" + departureDate.toString();

	        // Tạo HttpEntity mà không cần headers
	        HttpEntity<Void> entity = new HttpEntity<>(null);

	        // Gửi yêu cầu GET với các tham số trong URL
	        ResponseEntity<List<Schedule>> response = restTemplate.exchange(
	                url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Schedule>>() {});
	        
	        // Trả về kết quả từ API
	        return response.getBody();

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
//	@Override
//	public Schedule findById(int id) {
//		try {
//			String url = apiUrl + "api/schedule/id/" + id;
//	        
//	        HttpEntity<Void> entity = new HttpEntity<>(null);
//	        
//	        return restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<Schedule>() {}).getBody();
//		} catch (ResourceAccessException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}



}
