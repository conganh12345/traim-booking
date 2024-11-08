package com.backend.train_booking_backend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.train_booking_backend.models.Route;
import com.backend.train_booking_backend.repositories.RouteRepository;
import com.backend.train_booking_backend.services.IRouteService;

@Service
public class RouteService implements IRouteService {
	@Autowired
	private RouteRepository routeRepo;

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
	public boolean deleteRoute(Integer id) {
	    try {
	        if (routeRepo.existsById(id)) {
	        	routeRepo.deleteById(id);
	            return true;
	        } else {
	            System.out.println("Route with ID " + id + " not found.");
	            return false;
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Đã xảy ra lỗi khi xóa lịch trình.", e);
	    }
	}
}








