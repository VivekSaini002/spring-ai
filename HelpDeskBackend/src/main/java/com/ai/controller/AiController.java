package com.ai.controller;

import com.ai.service.AiService;
import com.ai.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/response")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping
    public ResponseEntity<String> getResponse(@RequestBody String query, @RequestHeader("conversationId") String conversationId){
        return ResponseEntity.ok(aiService.getResponseFromAssistant(query, conversationId));
    }

    @PostMapping("/stream")
    public Flux<String> getStreamResponse(@RequestBody String query, @RequestHeader("conversationId") String conversationId){
        return aiService.streamResponseFromAssistant(query, conversationId);
    }
}
