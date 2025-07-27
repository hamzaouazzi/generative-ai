package fr.ho.genai_demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StreamingController {
    private ChatClient chatClient;

    public StreamingController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_PLAIN_VALUE)
    public Flux<String> stream(String query) {
        return chatClient.prompt()
                .user(query)
                .stream()
                .content();
    }
}
