package com.ai.controller;

import com.ai.service.AiService;
import com.ai.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping
    public ResponseEntity<String> getResponse(@RequestBody String query){
        return ResponseEntity.ok(aiService.getResponseFromAssistant(query));
    }
}
