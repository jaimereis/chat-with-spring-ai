package com.br.study.spring_ai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.study.spring_ai.service.OpenAIChatService;

@RestController
@RequestMapping("/openai/api")
public class OpenAIChatController {

    private final OpenAIChatService openAIChatService;

    public OpenAIChatController(OpenAIChatService openAIChatService) {
        this.openAIChatService = openAIChatService;
    }

    @GetMapping("/chat")
    public String chatWithOpenAI(@RequestParam String message) {
        return openAIChatService.askToAI(message);
    }

}
