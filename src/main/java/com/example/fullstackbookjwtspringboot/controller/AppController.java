package com.example.fullstackbookjwtspringboot.controller;

import com.example.fullstackbookjwtspringboot.dto.SignUpRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    @GetMapping("")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }

    @PostMapping("/login")
    public String login() {
        return "jwt token";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpRequest signUpRequest) {
        System.out.println("signUpRequest = " + signUpRequest.getEmail());
        return "signup";
    }
}
