package com.backend.train_booking_backend.models;

import java.util.ArrayList;
import java.util.List;

import com.backend.train_booking_backend.models.enums.CoachStatus;
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
@Table(name = "coach")

public class Coach {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String coachName;

	@Column
	private String description;
	
	@Enumerated(EnumType.STRING)
	private CoachStatus status;
	
	@ManyToOne
	@JoinColumn(name = "train_id")
	private Train train;

	@OneToMany(mappedBy = "coach")
	@JsonIgnore
	private List<Seat> seat = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CoachStatus getStatus() {
		return status;
	}

	public void setStatus(CoachStatus status) {
		this.status = status;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public List<Seat> getSeat() {
		return seat;
	}

	public void setSeat(List<Seat> seat) {
		this.seat = seat;
	}
}
