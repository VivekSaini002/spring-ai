package com.ai.controllers;

import com.ai.service.SpeechToTextService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/convertor")
public class TextController {

    private final SpeechToTextService speechToTextService;

    public TextController(SpeechToTextService speechToTextService) {
        this.speechToTextService = speechToTextService;
    }

    @PostMapping
    public ResponseEntity<String> getTextFromSpeech(){
        Resource audio = new ClassPathResource("audio/sample.mp3");
        String text = speechToTextService.speechToText(audio);
        return ResponseEntity.ok(text);
    }

    @PostMapping("/transcribe")
    public ResponseEntity<String> transcribeUpload(@RequestParam("file") MultipartFile file) throws IOException {
        Resource audio = new InputStreamResource(file.getInputStream());
        String text = speechToTextService.speechToText(audio);
        return ResponseEntity.ok(text);
    }

}
