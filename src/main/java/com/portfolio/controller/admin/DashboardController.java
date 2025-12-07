package com.portfolio.controller.admin;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class DashboardController {
    
    @GetMapping("/dashboard")
    public String getDashboardPage(){
        return "<h1>Welcome to admin dashboard<h1>";
    }
}
