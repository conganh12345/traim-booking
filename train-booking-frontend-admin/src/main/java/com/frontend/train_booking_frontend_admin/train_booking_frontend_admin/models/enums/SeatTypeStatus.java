package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums;

public enum SeatTypeStatus {
	ENABLE("Còn chỗ", "badge-light-success"), 
    DISABLED("Hết chỗ", "badge-light-danger"); 

    private final String displayName;
    private final String badgeClass; 

    SeatTypeStatus(String displayName, String badgeClass) {
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
