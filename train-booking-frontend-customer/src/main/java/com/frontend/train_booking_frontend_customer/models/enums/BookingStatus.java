package com.frontend.train_booking_frontend_customer.models.enums;

public enum BookingStatus {
	 PENDING("Đang chờ", "badge-light-warning"),
    COMPLETED("Hoàn thành", "badge-light-success"),
    FAILED("Thất bại", "badge-light-danger"),
    CANCELLED("Đã hủy", "badge-light-secondary");

    private final String displayName;
    private final String badgeClass;

    BookingStatus(String displayName, String badgeClass) {
        this.displayName = displayName;
        this.badgeClass = badgeClass;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getBadgeClass() {
        return badgeClass;
    }
}
