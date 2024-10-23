package com.backend.train_booking_backend.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "train")
public class Train {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String trainname;

	@Column
	private int coachcount;

	@Column
	private String description;

	@OneToMany(mappedBy = "train")
	@JsonManagedReference("train-coaches")
	private List<Coach> details = new ArrayList<>();

	@ManyToMany(mappedBy = "trains")
	private List<Schedule> schedules = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTrainname() {
		return trainname;
	}

	public void setTrainname(String trainname) {
		this.trainname = trainname;
	}

	public int getCoachcount() {
		return coachcount;
	}

	public void setCoachcount(int coachcount) {
		this.coachcount = coachcount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Coach> getDetails() {
		return details;
	}

	public void setDetails(List<Coach> details) {
		this.details = details;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
}
