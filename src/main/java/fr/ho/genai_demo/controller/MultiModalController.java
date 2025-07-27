package fr.ho.genai_demo.controller;

import fr.ho.genai_demo.output.CnieModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class MultiModalController {
    private ChatClient chatClient;
    @Value("classpath:images/cnie.png")
    private Resource image;

    public MultiModalController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/describe")
    public CnieModel describeImage() {
        return chatClient.prompt()
                .system("Describe the image")
                .user(u->
                        u.text("Describe this image")
                                .media(MediaType.IMAGE_PNG, image))
                .call()
                .entity(CnieModel.class);
    }
    @PostMapping(value = "/askImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String askImage(@RequestParam(name = "file") MultipartFile file, String question) throws IOException {
        byte[] bytes = file.getBytes();
        return chatClient.prompt()
                .system("Answer the question of user about the image")
                .user(u->
                        u.text(question)
                                .media(MediaType.IMAGE_PNG, new ByteArrayResource(bytes))


                ).call()
                .content();
    }
}
