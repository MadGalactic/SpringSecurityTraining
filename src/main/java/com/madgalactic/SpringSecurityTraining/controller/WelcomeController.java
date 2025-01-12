package com.madgalactic.SpringSecurityTraining.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String hello(HttpServletRequest request) {
        return "Welcome to Mars" + request.getSession().getId();
    }

}
