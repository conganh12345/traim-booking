package com.backend.train_booking_backend.models;

import java.util.ArrayList;
import java.util.List;

import com.backend.train_booking_backend.models.enums.BookingStatus;
import com.backend.train_booking_backend.models.enums.TrainStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
	
	@Enumerated(EnumType.STRING)
	private TrainStatus status;

	@OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Coach> coachs = new ArrayList<>();

	@ManyToMany(mappedBy = "trains")
	@JsonManagedReference(value = "train-schedules") 
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


	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	
	public TrainStatus getStatus() {
		return status;
	}

	public void setStatus(TrainStatus status) {
		this.status = status;
	}

	public List<Coach> getCoachs() {
		return coachs;
	}

	public void setCoachs(List<Coach> coachs) {
		this.coachs = coachs;
	}
}
