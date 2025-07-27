package fr.ho.genai_demo.controller;


import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IMageGenerationController {
    private OpenAiImageModel openAiImageModel;

    public IMageGenerationController(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    @GetMapping("/generateImage")
    public String generateImage(String prompt) {
        ImageOptions imageOptions = OpenAiImageOptions.builder()
                .quality("4k")
                .model("dall-e-3")
                .width(3840)
                .height(2160 )
                .build();
        ImagePrompt imagePrompt = new ImagePrompt(prompt, imageOptions);
        return openAiImageModel.call(imagePrompt).getResult().getOutput().getUrl();
    }
}
