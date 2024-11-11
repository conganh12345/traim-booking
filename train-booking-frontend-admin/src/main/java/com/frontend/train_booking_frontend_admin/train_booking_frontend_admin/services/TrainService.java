package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Train;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.User;

import jakarta.servlet.http.HttpSession;

@Service
public class TrainService {
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
	
	public List<Train> getAllTrains(){
		// Call API to get all Trains
		try {
			setJwtToken();
			String url = apiUrl + "api/train";
			HttpEntity<User> entity = new HttpEntity<>(headers);

			// Get list train from API
			Train[] trains = restTemplate.exchange(url, HttpMethod.GET, entity, Train[].class).getBody();

			
			return Arrays.asList(trains);
		}catch (ResourceAccessException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean addTrain(Train train) {
	    try {
	        restTemplate.postForObject(apiUrl + "api/train", train, Train.class);
	        return true;  
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false; 
	    }
	}
	
	 public Train getTrainById(Integer id) { 
        try {
            return restTemplate.getForObject(apiUrl + "api/train/id/" + id, Train.class);
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
	 
	 public boolean updatetrain(Train train) { 
        try {
            restTemplate.put(apiUrl + "api/train/" + train.getId(), train);
            return true;
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
	 
	 public boolean deletetrain(Integer id) {
        try {
            restTemplate.delete(apiUrl + "api/train/" + id);
            return true; 
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            return false; 
        }
    }
}
