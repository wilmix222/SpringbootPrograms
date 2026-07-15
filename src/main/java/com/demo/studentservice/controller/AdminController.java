package com.demo.studentservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "ADMIN dashboard ✅";
    }
}