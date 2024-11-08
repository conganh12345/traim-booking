package com.frontend.train_booking_frontend_customer.models;

import com.frontend.train_booking_frontend_customer.models.enums.ERole;

public class User 
{
	private Integer id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
    private ERole role;

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Getters and Setters
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

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}
}

