package com.frontend.train_booking_frontend_customer.services.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.frontend.train_booking_frontend_customer.models.Booking;
import com.frontend.train_booking_frontend_customer.services.IBookingService;

import jakarta.servlet.http.HttpSession;

@Service
public class BookingService implements IBookingService{

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

	
	public Booking findByCode(String code) {
		try {
			setJwtToken();
			String url = apiUrl + "api/booking/code/" + code;
	        
	        HttpEntity<Void> entity = new HttpEntity<>(headers);
	        
	        return restTemplate.exchange(url, HttpMethod.GET, entity, Booking.class).getBody();
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public Booking insertBooking(Booking booking) {
		try {
            setJwtToken();
            String url = apiUrl + "api/booking";
            HttpEntity<Booking> entity = new HttpEntity<>(booking, headers);
            Booking booking2 = restTemplate.postForObject(url, entity, Booking.class);
            return booking2;
        } catch (ResourceAccessException | HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	@Override
	public boolean updateBooking(Booking booking) {
	    try {
	        setJwtToken();
	        
	        String url = apiUrl + "api/booking/" + booking.getId();
	        
	        HttpEntity<Booking> entity = new HttpEntity<>(booking, headers);
	        
	        restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
	        return true;
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	@Override
	public String getVNPayByBookingId(int bookingId) {
	    try {
	        String url = apiUrl + "api/vnpay/create_payment/" + bookingId;

	        return restTemplate.postForObject(url, null, String.class);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public Booking processPayment(int bookingId) {
	    try {
	        String url = apiUrl + "api/vnpay/payment-return/" + bookingId;

	        return restTemplate.getForObject(url, Booking.class);
	    } catch (ResourceAccessException | HttpClientErrorException e) {
	        e.printStackTrace();
	        return null;
	    }
	}


	@Override
	public List<Booking> getBookingsByUser(int userId) {
		try {
			setJwtToken();
			String url = apiUrl + "api/booking/userid/" + userId;
			HttpEntity<Booking> entity = new HttpEntity<>(headers);
			Booking[] bookings = restTemplate.exchange(url, HttpMethod.GET, entity, Booking[].class).getBody();

			return Arrays.asList(bookings);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
