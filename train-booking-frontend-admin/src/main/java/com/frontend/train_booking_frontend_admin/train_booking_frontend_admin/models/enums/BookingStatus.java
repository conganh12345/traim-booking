package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums;

public enum BookingStatus {
	PENDING("Đang chờ"),         
    COMPLETED("Hoàn thành"),       
    FAILED("Thất bại"),          
    CANCELLED("Đã hủy");    
    
    private final String displayName;

    BookingStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
