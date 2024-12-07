package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums;

public enum RouteStatus {
	ACTIVE("Hoạt động", "badge-light-success"),
    INACTIVE("Không hoạt động", "badge-light-danger");

    private final String displayName;
    private final String badgeClass;

    RouteStatus(String displayName, String badgeClass) {
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
