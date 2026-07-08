package com.ai.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class WeatherTool {
    
    @Value("${app.weather.api-key}")
    private String weatherApiKey;

    private RestClient restClient;
    
    public  WeatherTool(RestClient restClient) {
        this.restClient = restClient;
    }
    
    @Tool(description = "Get weather information for given city.")
    public String getWeather(@ToolParam(description = "city of which we want to weather information.") String city){
        System.out.println("Getting weather information for "+city);
        var response = restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/current.json")
                        .queryParam("key", weatherApiKey)
                        .queryParam("q", city)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String,Object>>() {
                });
        return response.toString();
                
    }
}
