package com.ai;

import com.ai.tools.WeatherTool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ToolCallingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private	WeatherTool weatherTool;

	@Test
	void getWeatherTest() {
		var response = weatherTool.getWeather("Delhi India");
		System.out.println(response);
	}

}
