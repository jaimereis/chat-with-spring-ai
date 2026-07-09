package com.br.study.spring_ai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.study.spring_ai.service.MultiModelChatService;

@RestController
@RequestMapping("/multi-model/api")
public class MultiModelChatController {

    private final MultiModelChatService multiModelChatService;

    public MultiModelChatController(MultiModelChatService multiModelChatService) {
        this.multiModelChatService = multiModelChatService;
    }

    @GetMapping("/chat/openai")
    public String chatWithOpenAI(@RequestParam String message) {
        return multiModelChatService.chatWithOpenAI(message);
    }

    @GetMapping("/chat/ollama")
    public String chatWithOllama(@RequestParam String message) {
        return multiModelChatService.chatWithOllama(message);
    }

}
