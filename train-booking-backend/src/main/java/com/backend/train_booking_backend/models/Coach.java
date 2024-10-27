package com.backend.train_booking_backend.models;

import java.util.ArrayList;
import java.util.List;

import com.backend.train_booking_backend.models.enums.CoachStatus;
import com.backend.train_booking_backend.models.enums.TicketStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
	private int seatCount;

	@Column
	private String description;
	
	@Enumerated(EnumType.STRING)
	private CoachStatus status;

	@ManyToOne
	@JoinColumn(name = "train_id")
	@JsonBackReference
	private Train train;

	@OneToMany(mappedBy = "coach")
	@JsonManagedReference("coach-seats")
	private List<SeatType> seatTypes = new ArrayList<>();

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

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
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

	public List<SeatType> getSeatTypes() {
		return seatTypes;
	}

	public void setSeatTypes(List<SeatType> seatTypes) {
		this.seatTypes = seatTypes;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}
}
