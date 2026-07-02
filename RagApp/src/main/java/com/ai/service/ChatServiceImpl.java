package com.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService{

    private final ChatClient chatClient;

    @Value("classpath:/prompts/user-message.st")
    private Resource userMessage;

    @Value("classpath:/prompts/system-message.st")
    private Resource systemMessage;

    @Autowired
    private VectorStore vectorStore;

    public ChatServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String chatTemplate(String query, String userId) {


        return this.chatClient

                .prompt()
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID,userId))
//                .advisors(new SimpleLoggerAdvisor())
                .system(system ->
                        system.text(this.systemMessage))
                .user(user ->
                        user.text(this.userMessage).param("concept", query))
                .call()
                .content()
                ;
    }

    @Override
    public Flux<String> streamChat(String query) {

        return  this.chatClient
                .prompt()
                .system(system-> system.text(this.systemMessage))
                .user(user-> user.text(this.userMessage).param("concept",query))
                .stream()
                .content();


    }

    @Override
    public void saveData(List<String> list) {
        List<Document> documentList = list.stream().map(Document::new).toList();
        this.vectorStore.add(documentList);

    }
}
