package com.ai.service;

import com.ai.tools.SimpleDataTimeTool;
import com.ai.tools.WeatherTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private ChatClient chatClient;
    @Autowired
    private WeatherTool weatherTool;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    // chat method::: get response from llm model
    //chatClient:: client for calling llm model
    // tool description : chatbot for tool calling
    public String chat(String q) {
        return chatClient
                .prompt()
                .tools(new SimpleDataTimeTool(), weatherTool)
                .user(q)
                .call()
                .content();
    }

}