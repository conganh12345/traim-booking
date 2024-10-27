package com.backend.train_booking_backend.models.enums;

public enum CoachStatus {
	AVAILABLE,        // Sẵn sàng để sử dụng
    IN_SERVICE,       // Đang chở hành khách trong chuyến đi
    MAINTENANCE,      // Đang bảo trì, không thể sử dụng
    OUT_OF_ORDER,     // Hỏng hóc, cần sửa chữa
    DECOMMISSIONED    // Ngưng hoạt động, không còn được sử dụng
}
