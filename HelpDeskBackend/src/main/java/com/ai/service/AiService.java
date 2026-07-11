package com.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiService {

    private final ChatClient chatClient;

    public String getResponseFromAssistant(String query){
        return chatClient.prompt()
                .user(query)
                .call()
                .content();
    }
}
