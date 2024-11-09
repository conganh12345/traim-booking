package com.backend.train_booking_backend.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "train")
@JsonIgnoreProperties("coachs")
public class Train {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String trainName;

	@Column
	private String description;

	@OneToMany(mappedBy = "train")
	@JsonIgnore
	private List<Coach> coach = new ArrayList<>();

	@OneToMany(mappedBy = "train")
	@JsonIgnore
	private List<Schedule> schedule = new ArrayList<>();

	@OneToMany(mappedBy = "train")
	@JsonIgnore
	private List<Route> route = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Coach> getCoach() {
		return coach;
	}

	public void setCoach(List<Coach> coach) {
		this.coach = coach;
	}

	public List<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}

	public List<Route> getRoute() {
		return route;
	}

	public void setRoute(List<Route> route) {
		this.route = route;
	}
}
