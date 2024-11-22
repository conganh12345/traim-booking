package com.backend.train_booking_backend.services;

import java.time.LocalDate;
import java.util.List;

import com.backend.train_booking_backend.models.Route;
import com.backend.train_booking_backend.models.enums.Province;

public interface IRouteService {
	List<Route> getAllRoutes();

	Route getRoute(Integer id);

	Route addRoute(Route route);

	Route updateRoute(Integer id, Route route);

	int deleteRoute(Integer id);
}
