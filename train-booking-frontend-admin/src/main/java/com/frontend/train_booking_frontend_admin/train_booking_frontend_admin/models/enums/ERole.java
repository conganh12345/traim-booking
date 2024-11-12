package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums;

public enum ERole {
    USER("Người Dùng", "badge-light-info"),
    ADMIN("Quản Trị Viên", "badge-light-secondary");

    private final String displayName;
    private final String badgeClass;

    ERole(String displayName, String badgeClass) {
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