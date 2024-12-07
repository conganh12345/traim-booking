package com.backend.train_booking_backend.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.backend.train_booking_backend.models.Booking;

@Service
public class SendEmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	public SendEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
	
	public void sendOrderDetails(String to, Booking booking) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject("Thông tin đơn hàng #" + booking.getCode());

        String emailContent = buildOrderDetailsEmail(booking);
        message.setText(emailContent);

        message.setFrom("anhvca1234@gmail.com");
        mailSender.send(message);
    }


    private String buildOrderDetailsEmail(Booking booking) {
        StringBuilder emailContent = new StringBuilder();
        
        String formattedDate = formatBookingTime(booking.getBookingTime());
        emailContent.append("Xin chào,\n\n");
        emailContent.append("Cảm ơn bạn đã đặt vé trên hệ thống của chúng tôi. Dưới đây là thông tin đơn hàng của bạn:\n\n");
        emailContent.append("Mã đơn hàng: ").append(booking.getCode()).append("\n");
        emailContent.append("Tổng giá trị: ").append(booking.getTotalPrice()).append(" VND\n");
        emailContent.append("Ngày đặt: ").append(formattedDate).append("\n");
        emailContent.append("Trạng thái: ").append(booking.getStatus().getDisplayName()).append("\n\n");
        emailContent.append("Thông tin khách hàng:\n");
        emailContent.append("- Họ tên: ").append(booking.getUser().getFullName()).append("\n");
        emailContent.append("- Email: ").append(booking.getUser().getEmail()).append("\n");
        emailContent.append("- Số điện thoại: ").append(booking.getUser().getPhoneNumber()).append("\n\n");
        emailContent.append("Chúc bạn có một hành trình vui vẻ!\n");
        emailContent.append("Trân trọng,\n");
        emailContent.append("Đội ngũ hỗ trợ khách hàng.");
        return emailContent.toString();
    }
    
    private String formatBookingTime(LocalDateTime bookingTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return bookingTime.format(formatter); 
    }
}
