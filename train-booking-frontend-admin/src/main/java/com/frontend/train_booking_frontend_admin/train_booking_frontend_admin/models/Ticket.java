package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.TicketStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Ticket {
	private Integer id;

	@NotEmpty(message = "Tên vé không được để trống")
    @Size(min = 3, max = 50, message = "Tên vé phải có độ dài từ 3 đến 50 ký tự")
    private String ticketName;

	@NotNull(message = "Giá vé không được để trống")
    @Min(value = 0, message = "Giá vé phải lớn hơn hoặc bằng 0")
    private double priceTicket;

	@NotNull(message = "Trạng thái không được để trống")
    private TicketStatus status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTicketname() {
		return ticketName;
	}

	public void setTicketname(String ticketName) {
		this.ticketName = ticketName;
	}

	
	public TicketStatus getStatus() {
		return status;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public double getPriceTicket() {
		return priceTicket;
	}

	public void setPriceTicket(double priceTicket) {
		this.priceTicket = priceTicket;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}
}
