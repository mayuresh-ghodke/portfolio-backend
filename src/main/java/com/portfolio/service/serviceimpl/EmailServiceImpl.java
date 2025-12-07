package com.portfolio.service.serviceimpl;

import com.portfolio.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    private String generatedOtp;
    private LocalDateTime otpExpiryTime;

    private final Random random = new Random();

    // ------------------ Send any simple email ------------------
    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toEmail);
        msg.setFrom(fromEmail);
        msg.setSubject(subject);
        msg.setText(body);
        javaMailSender.send(msg);
    }

    // ------------------ Registration success email ------------------
    public void sendRegistrationEmail(String toEmail, String name) {
        String subject = "Registration Successful";
        String body = "Hello " + name + ",\n\nYour registration was successful!\n\nThank you.";
        sendEmail(toEmail, subject, body);
    }

    // ------------------ Contact form message to admin ------------------
    public void sendContactMessage(String fromUserEmail, String name, String messageContent) {
        String subject = "PORTFOLIO WEBSITE MESSAGE - New Contact Message from " + name;

        String body = "You have received a new message from your portfolio website.\n\n" +
                "Sender Name: " + name + "\n" +
                "Sender Email: " + fromUserEmail + "\n\n" +
                "Message:\n" + messageContent + "\n\n" +
                "Please respond promptly.";

        sendEmail(fromEmail, subject, body); // send to yourself/admin
    }


    // ------------------ Generate & send OTP ------------------
    public String sendOtp(String toEmail) {

        String generatedOtp = generateOtp();

        String subject = "OTP for Password Reset";
        String body = "Your OTP code is: " + generatedOtp + "\nValid for 5 minutes.";

        sendEmail(toEmail, subject, body);
        return generatedOtp;
    }

    // ------------------ Verify OTP ------------------
    public boolean verifyOtp(String otp) {
        if (generatedOtp == null || otpExpiryTime == null) return false;
        if (LocalDateTime.now().isAfter(otpExpiryTime)) return false;

        boolean valid = otp.equals(generatedOtp);
        if (valid) {
            generatedOtp = null;
            otpExpiryTime = null;
        }
        return valid;
    }

    @Override
    public String generateOtp() {
        generatedOtp = String.format("%06d", random.nextInt(999999));
        otpExpiryTime = LocalDateTime.now().plusMinutes(5);
        return generatedOtp;
    }
}
