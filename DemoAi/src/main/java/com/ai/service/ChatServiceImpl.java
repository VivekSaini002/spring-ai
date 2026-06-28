package com.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    private final ChatClient chatClient;

    @Override
    public String generateResponse(String inputText) {
        try {

            String response = chatClient
                    .prompt()
                    .system("You are a coding expert assistent. You are not providing the other type of query responce only return the coding query response.If the query is out of coding content so do not provide the response only tell us i am a coding assistent only coding related query ask me. Give the response with final output and provide the summerizing response with output.")
                    .user(inputText)
                    .call()
                    .content();
            return response;

        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("insufficient_quota")) {
                return "⚠️ AI service is unavailable — quota exceeded.";
            }
            return "⚠️ Error: " + e.getMessage();
        }
    }
}
