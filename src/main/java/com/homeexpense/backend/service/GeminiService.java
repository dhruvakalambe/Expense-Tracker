package com.homeexpense.backend.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.generativeai.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class GeminiService {

    private final GenerativeModel model;

    public GeminiService() throws Exception {
        InputStream credentialsStream = getClass().getResourceAsStream("/gemini-service-account.json");

        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream)
                .createScoped("https://www.googleapis.com/auth/cloud-platform");

        GoogleGenerativeAISettings settings = GoogleGenerativeAISettings.builder()
                .setCredentialsProvider(() -> credentials)
                .build();

        this.model = GenerativeModel.newBuilder()
                .setModelName("gemini-pro")
                .setSettings(settings)
                .build();
    }

    public String askGemini(String input) {
        try {
            GenerateContentResponse response = model.generateContent(ContentMaker.fromText(input));
            return response.getText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Gemini error: " + e.getMessage();
        }
    }
}
