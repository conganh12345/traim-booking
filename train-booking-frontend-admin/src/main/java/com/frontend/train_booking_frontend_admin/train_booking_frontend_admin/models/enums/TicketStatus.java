package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums;

public enum TicketStatus {
    ENABLE("Đã duyệt", "badge-light-success"), 
    DISABLED("Chưa duyệt", "badge-light-danger"); 

    private final String displayName;
    private final String badgeClass; 

    TicketStatus(String displayName, String badgeClass) {
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
