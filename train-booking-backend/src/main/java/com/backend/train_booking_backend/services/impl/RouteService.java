package com.backend.train_booking_backend.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.train_booking_backend.models.Route;
import com.backend.train_booking_backend.models.enums.Province;
import com.backend.train_booking_backend.repositories.RouteRepository;
import com.backend.train_booking_backend.repositories.StationRepository;
import com.backend.train_booking_backend.services.IRouteService;

@Service
public class RouteService implements IRouteService {
	@Autowired
	private RouteRepository routeRepo;
	
	@Autowired
	private StationRepository stationRepo;

	@Override
	public List<Route> getAllRoutes() {
		return routeRepo.findAll();
	}

	@Override
	public Route getRoute(Integer id) {
		return routeRepo.findById(id).get();
	}

	@Override
	public Route addRoute(Route route) {
		try {
			return routeRepo.save(route);
		} catch (Exception e) {
			 e.printStackTrace();
			throw new RuntimeException("Đã xảy ra lỗi khi thêm lịch trình.", e);
		}
	}

	@Override
	public Route updateRoute(Integer id, Route route) {
		try {
			Optional<Route> oldRouteOpt = routeRepo.findById(id);
			if (oldRouteOpt.isPresent()) {
				route.setId(id);
			}
			return routeRepo.save(route);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa lịch trình.", e);
		}
	}

	@Override
	@Transactional
	public int deleteRoute(Integer id) {
		try {
	        if (routeRepo.existsById(id)) {
	            int seatCount = stationRepo.countByRouteId(id); 

	            if (seatCount > 0) {
	                System.out.println("Không thể xóa tuyến đường với ID " + id + " vì vẫn còn vé liên kết.");
	                return 0; 
	            }

	            routeRepo.deleteById(id);

	            if (!routeRepo.existsById(id)) {
	                System.out.println("Đã xóa tuyến đường với ID " + id);
	                return 1;  
	            } else {
	                System.out.println("Không thể xóa tuyến đường với ID " + id);
	                return 0; 
	            }
	        } else {
	            System.out.println("Tuyến đường với ID " + id + " không tồn tại.");
	            return 2;  
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0; 
	    }
	}

}








