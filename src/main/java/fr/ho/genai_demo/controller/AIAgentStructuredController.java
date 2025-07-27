package fr.ho.genai_demo.controller;

import fr.ho.genai_demo.output.MovieList;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIAgentStructuredController {
    private ChatClient chatClient;

    public AIAgentStructuredController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @GetMapping("/askAgent")
    public MovieList askLLM(String query) {
        String systemMsg = """
                You are a cinema specialist
                Answer the questions about it
                """;
        return chatClient.prompt()
                .system(systemMsg)
                .user(query)
                .call()
                .entity(MovieList.class);
    }
}
