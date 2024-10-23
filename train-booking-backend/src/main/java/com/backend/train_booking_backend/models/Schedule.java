package com.backend.train_booking_backend.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "schedule")
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private LocalDateTime departureDate;

	@Column
	private String destinationStation;

	@Column
	private String scheduleName;

	@Column
	private LocalDateTime estimateArrivalDate;

	@ManyToOne
	@JoinColumn(name = "station_id")
	@JsonBackReference 
	private Station station;

	@OneToMany(mappedBy = "schedule")
	@JsonManagedReference
	private List<Booking> details = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "schedule_train", joinColumns = @JoinColumn(name = "schedule_id"), inverseJoinColumns = @JoinColumn(name = "train_id"))
	private List<Train> trains = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}

	public String getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}
	
	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public LocalDateTime getEstimateArrivalDate() {
		return estimateArrivalDate;
	}

	public void setEstimateArrivalDate(LocalDateTime estimateArrivalDate) {
		this.estimateArrivalDate = estimateArrivalDate;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public List<Train> getTrains() {
		return trains;
	}

	public void setTrains(List<Train> trains) {
		this.trains = trains;
	}

	public List<Booking> getDetails() {
		return details;
	}

	public void setDetails(List<Booking> details) {
		this.details = details;
	}
}
