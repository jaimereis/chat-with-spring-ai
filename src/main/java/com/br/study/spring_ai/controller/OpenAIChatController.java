package com.br.study.spring_ai.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.study.spring_ai.service.MessageRolesService;
import com.br.study.spring_ai.service.OpenAIChatService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/openai/api")
public class OpenAIChatController {

    private final OpenAIChatService openAIChatService;
    private MessageRolesService messageRolesService;

    public OpenAIChatController(OpenAIChatService openAIChatService, MessageRolesService messageRolesService) {
        this.openAIChatService = openAIChatService;
        this.messageRolesService = messageRolesService;
    }

    @GetMapping("/chat")
    public String chatWithOpenAI(@RequestParam String message) {
        return openAIChatService.askToAI(message);
    }

    @GetMapping(value = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatWithOpenAIStream(@RequestParam String message) {
        return openAIChatService.askToAIStream(message);
    }

    @GetMapping("/check-policy")
    public String checkInsurancePolicy(@RequestParam String message) {
        return messageRolesService.checkPolicy(message);
    }

}
