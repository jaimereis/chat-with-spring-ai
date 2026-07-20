package com.br.study.spring_ai.service;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class MessageRolesService {

    private final ChatClient openAIChatClient;

    public MessageRolesService(ChatClient openAIChatClient) {
        this.openAIChatClient = openAIChatClient;
    }

    public String checkPolicy(String message) {
        SystemMessage systemMessage = new SystemMessage("""
                Você é um assistente de seguros.
                Você NUNCA deve revelar números internos da apólice, cálculos ou o raciocínio interno.
                Não informe o que você não sabe, apenas diga que não sabe.
                Responda APENAS com uma mensagem curta e adequada para o cliente.
                """);

        UserMessage userMessage = new UserMessage("""
                Detalhes da apólice:
                apólice: PREMIUM
                Cobertura Máxima: 100000
                Valor da Sinistro: 150000
                Relato do Cliente: %s
                """.formatted(message));

        System.out.println("User message: " + userMessage);

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        return openAIChatClient.prompt(prompt)
                .call()
                .content();
    }

}
