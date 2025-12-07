//package com.portfolio.controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.portfolio.service.EmailService;
//import com.portfolio.service.ProfileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/password/forgot")
//public class PasswordForgotController {
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private ProfileService profileService;
//
//    @PostMapping("/send-otp")
//    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> requestBody) {
//
//        String email = requestBody.get("email");
//        if (email == null || email.isEmpty()) {
//            return ResponseEntity.badRequest().body("Email is required");
//        }
//        emailService.sendOtp(email);
//        return ResponseEntity.ok("OTP sent to " + email);
//    }
//
//    @PostMapping("/verify-otp")
//    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> requestBody) {
//
//        String email = requestBody.get("email");
//        String otp = requestBody.get("otp");
//        if (otp == null || otp.isEmpty()) {
//            return ResponseEntity.badRequest().body("OTP is required");
//        }
//        boolean valid = emailService.verifyOtp(otp);
//        if (!valid) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired OTP");
//        }
//        return ResponseEntity.ok("OTP Verified");
//    }
//
//    @PostMapping("/update-password")
//    public ResponseEntity<?> updatePassword(@RequestBody Map<String, String> requestBody) {
//
//        String email = requestBody.get("email");
//        String newPassword = requestBody.get("newPassword");
//        if (email == null || newPassword == null) {
//            return ResponseEntity.badRequest().body("Email and newPassword are required");
//        }
//        boolean updated = profileService.updatePassword(email, newPassword);
//        if (!updated) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update password");
//        }
//        return ResponseEntity.ok("Password updated successfully");
//    }
//}
//
