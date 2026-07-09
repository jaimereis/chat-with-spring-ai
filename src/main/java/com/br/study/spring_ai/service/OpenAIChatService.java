package com.br.study.spring_ai.service;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class OpenAIChatService {

    private final ChatClient openAIChatClient;

    public OpenAIChatService(ChatClient openAIChatClient) {
        this.openAIChatClient = openAIChatClient;
    }


    /**
     * Envia uma mensagem para o modelo de linguagem da OpenAI e retorna a resposta gerada.
     *
     * @param message A mensagem (prompt) a ser enviada ao modelo.
     * @return O conteúdo textual gerado pelo modelo como resposta.
     *
     * Opções configuradas:
     *
     * model: Define qual modelo da OpenAI será utilizado para gerar a resposta.
     *
     * temperature: Controla a aleatoriedade/criatividade das respostas.
     *  0.0 → respostas determinísticas, sempre escolhe o token mais provável (mais previsível/conservador)
     *  0.1 → muito baixo, respostas quase sempre idênticas, focadas em previsões/precisas
     *  0.5 → equilíbrio entre criatividade e coerência
     *  1.0 → respostas mais criativas e variadas
     *  2.0 → muito aleatório, pode gerar respostas inconsistentes
     *
     * maxTokens: Limita o número máximo de tokens (palavras/partes de palavras) que o modelo pode gerar na resposta
     *
     * frequencyPenalty: Penaliza tokens que já apareceram com frequência
     * no texto gerado até o momento. Valores positivos (0 a 2) reduzem a probabilidade
     * de o modelo repetir as mesmas palavras ou frases, incentivando maior diversidade
     * vocabular na resposta.
     *
     * presencePenalty: Penaliza tokens que já apareceram ao menos uma vez
     * na resposta, independentemente da frequência. Valores positivos (0 a 2) incentivam
     * o modelo a introduzir novos conceitos e tópicos, evitando que ele fique "preso"
     * em um mesmo assunto durante a geração.
     *
     * stopSequences: Define uma lista de sequências de parada. Quando o modelo gerar qualquer uma dessas sequências, ele interrompe imediatamente a geração de texto.
     */
    public String askToAI(String message) {
        return openAIChatClient
                .prompt(message)
                .options(OpenAiChatOptions.builder()
                        .model("gpt-4o-mini")
                        .temperature(0.1)
                        .maxTokens(21)
                        .frequencyPenalty(0.7)
                        .presencePenalty(0.7)
                        .stopSequences(List.of("}")))
                .call()
                .content();
    }

}
