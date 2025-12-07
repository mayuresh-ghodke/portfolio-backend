package com.portfolio.service;

public interface EmailService {

    void sendEmail(String toEmail, String subject, String body);

    void sendRegistrationEmail(String toEmail, String name);

    void sendContactMessage(String fromUserEmail, String name, String messageContent);

    String sendOtp(String toEmail);

    boolean verifyOtp(String otp);

    String generateOtp();
}
