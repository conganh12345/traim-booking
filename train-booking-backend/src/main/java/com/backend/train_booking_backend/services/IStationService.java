package com.backend.train_booking_backend.services;

import java.util.List;

import com.backend.train_booking_backend.models.Station;

public interface IStationService {
	List<Station> getAllStations();

	Station getStation(Integer id);

	Station addStation(Station station);

	Station updateStation(Integer id, Station station);

	boolean deleteStation(Integer id);
}
