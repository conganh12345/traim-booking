package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums;

public enum CoachStatus {
    AVAILABLE("Sẵn Sàng", "badge-light-success"),
    IN_SERVICE("Đang Hoạt Động", "badge-light-primary"),
    MAINTENANCE("Bảo Trì", "badge-light-warning"),
    OUT_OF_ORDER("Hỏng", "badge-light-danger"),
    DECOMMISSIONED("Ngưng Hoạt Động", "badge-light-danger");

    private final String displayName;
    private final String badgeClass;

    CoachStatus(String displayName, String badgeClass) {
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