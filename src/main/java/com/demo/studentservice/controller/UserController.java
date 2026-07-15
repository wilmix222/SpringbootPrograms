package com.demo.studentservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String profile() {
        return "USER profile ✅";
    }

    @GetMapping("/only-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String onlyAdmin() {
        return "Only ADMIN can access ✅";
    }
}