package com.ai.controller;

import com.ai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AiController {

    private final ChatService chatService;

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam("q") String q) {
        String responseText = chatService.generateResponse(q);
//    	var responseText = chatClient.prompt(q).call().content();
        return ResponseEntity.ok(responseText);
    }

}
