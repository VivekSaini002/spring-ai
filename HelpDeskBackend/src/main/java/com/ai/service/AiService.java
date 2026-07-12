package com.ai.service;

import com.ai.tools.EmailTool;
import com.ai.tools.TicketDatabaseTool;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiService {

    @Value("classpath:/prompts/helpdesk-system-prompt.st")
    private Resource systemPrompt;

    private final TicketDatabaseTool ticketDatabaseTool;

    private final EmailTool emailTool;

    private final ChatClient chatClient;

    public String getResponseFromAssistant(String query, String conversationId){
        return chatClient.prompt()
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, conversationId))
                .tools(ticketDatabaseTool, emailTool)
                .system(systemPrompt)
                .user(query)
                .call()
                .content();
    }
}
