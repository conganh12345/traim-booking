package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums.ERole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public class User 
{
	private Integer id;
	
	@NotEmpty(message = "Tên người dùng không được để trống")
    @Size(min = 3, max = 50, message = "Tên người dùng phải có độ dài từ 3 đến 50 ký tự")
    private String fullName;

    @NotEmpty(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotEmpty(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Số điện thoại không hợp lệ")
    private String phoneNumber;

    @NotEmpty(message = "Địa chỉ không được để trống")
    private String address;

    @NotEmpty(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    private String password;
    
    @NotEmpty(message = "CMND/CCCD không được để trống")
    @Size(min = 6, message = "CMND/CCCD phải có ít nhất 6 ký tự")
	private String identifyCard;
    
    @NotNull(message = "Trạng thái không được để trống")
    private ERole role;

    public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIdentifyCard() {
		return identifyCard;
	}

	public void setIdentifyCard(String identifyCard) {
		this.identifyCard = identifyCard;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
