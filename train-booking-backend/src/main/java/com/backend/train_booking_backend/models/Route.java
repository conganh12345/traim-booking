package com.backend.train_booking_backend.models;

import java.util.ArrayList;
import java.util.List;

import com.backend.train_booking_backend.models.enums.Province;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "route")
public class Route {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String routeName;
	
	@Column
	private Double price;

	@Enumerated(EnumType.STRING)
	private Province departureLocation;
	
	@Enumerated(EnumType.STRING)
	private Province destinationLocation;

	@OneToMany(mappedBy = "route")
	@JsonIgnore
	private List<Station> station = new ArrayList<>();
	
	@OneToMany(mappedBy = "route")
	@JsonIgnore
	private List<Schedule> schedule = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "train_id")
	private Train train;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Province getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(Province departureLocation) {
		this.departureLocation = departureLocation;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Province getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(Province destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public List<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}

	public List<Station> getStation() {
		return station;
	}

	public void setStation(List<Station> station) {
		this.station = station;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
}
