package com.ai;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ai.helper.Helper;
import com.ai.service.ChatService;

@SpringBootTest
class RagAppApplicationTests {
	
	@Autowired
	private ChatService chatService;
	

	@Test
	void contextLoads() {
		System.out.println("Test run successfully.");
		this.chatService.saveData(Helper.getData());
		System.out.println("Data saved successfully.");
	}

}
