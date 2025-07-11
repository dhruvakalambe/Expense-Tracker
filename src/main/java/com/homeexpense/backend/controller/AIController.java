package com.homeexpense.backend.controller;

import com.homeexpense.backend.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Autowired
    private GeminiService geminiService;

    @PostMapping("/ask")
    public String ask(@RequestBody String message) {
        return geminiService.askGemini(message);
    }
}