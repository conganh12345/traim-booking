package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums;

public enum Province {
    HANOI("Hà Nội"),
    HOCHIMINH("TP Hồ Chí Minh"),
    DANANG("Đà Nẵng"),
    HAIPHONG("Hải Phòng"),
    CANTHO("Cần Thơ"),
	QUANGNINH("Quảng Ninh"),
	NGHEAN("Ga Vinh"),
	NINHBINH("Ninh Bình"),
	NAMDINH("Nam Định");

	 private final String displayName;

	 Province(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}