package com.example.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Board Application! Visit /api/health for API status.";
    }

    @GetMapping("/api")
    public String apiRoot() {
        return "Welcome to Board API!";
    }

    @GetMapping("/api/health")
    public String health() {
        return "Board Application is running!";
    }
}