package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums;

public enum TrainStatus {
	AVAILABLE("Sẵn Sàng", "badge-light-success"),
    IN_SERVICE("Đang Hoạt Động", "badge-light-primary"),
    MAINTENANCE("Bảo Trì", "badge-light-warning"),
    DECOMMISSIONED("Ngưng Hoạt Động", "badge-light-danger");

    private final String displayName;
    private final String badgeClass;

    TrainStatus(String displayName, String badgeClass) {
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
