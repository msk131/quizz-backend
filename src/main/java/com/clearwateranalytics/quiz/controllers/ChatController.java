package com.clearwateranalytics.quiz.controllers;

import io.swagger.annotations.ApiOperation;
import com.clearwateranalytics.quiz.pojo.dto.ChatRequestDto;
import com.clearwateranalytics.quiz.pojo.dto.ChatResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChatController {
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @GetMapping("/chat")
    @ApiOperation(value = "chatWithGPT")
    public String chat(@RequestParam String prompt) {
        ChatRequestDto request = new ChatRequestDto(model, prompt);

        ChatResponseDto response = restTemplate.postForObject(apiUrl, request, ChatResponseDto.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }

        // return the first response
        return response.getChoices().get(0).getMessage().getContent();
//        return response;
    }
}
