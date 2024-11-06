package com.backend.train_booking_backend.services;

import java.util.List;

import com.backend.train_booking_backend.models.Route;

public interface IRouteService {
	List<Route> getAllRoutes();

	Route getRoute(Integer id);

	Route addRoute(Route route);

	Route updateRoute(Integer id, Route route);


	boolean deleteRoute(Integer id);
}
