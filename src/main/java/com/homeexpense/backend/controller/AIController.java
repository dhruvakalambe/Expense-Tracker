package com.homeexpense.backend.controller;

import com.homeexpense.backend.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin
public class AIController {

    @Autowired
    private GeminiService geminiService;

    @PostMapping("/ask")
    public String askGemini(@RequestBody String prompt) {
        return geminiService.askGemini(prompt);
    }
}
