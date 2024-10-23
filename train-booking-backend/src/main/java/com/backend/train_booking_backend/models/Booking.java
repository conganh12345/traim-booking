package com.backend.train_booking_backend.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String fullName;

	@Column
	private LocalDateTime bookingTime;

	@Column
	private int paymentMethod;

	@Column
	private int status;

	@Column
	private String depatureStation;

	@Column
	private String destinationStation;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference 
	private User user;

	@OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<TicketBookingDetail> details = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "payment_id")
	@JsonBackReference 
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "schedule_id")
	@JsonBackReference 
	private Schedule schedule;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDateTime getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}

	public int getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDepatureStation() {
		return depatureStation;
	}

	public void setDepatureStation(String depatureStation) {
		this.depatureStation = depatureStation;
	}

	public String getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}

	public List<TicketBookingDetail> getDetails() {
		return details;
	}

	public void setDetails(List<TicketBookingDetail> details) {
		this.details = details;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
}
