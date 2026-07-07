package com.ai;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DockerModelApplicationTests {

	@Autowired
	private ChatClient chatClient;

	@Test
	void testChatClient() {
		System.out.println("Started testing chat client");
		String query = "write java program to print hello world";
		var response = this.chatClient.prompt()
				.user(query)
				.call()
				.content();
		System.out.println(response);

	}

}
