package ru.darkalive.LightLMS.controllers.rest.directories;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
public class AIController {

    OpenAiService service = new OpenAiService(getOpenAiKey());

    private String getOpenAiKey() {

        String key = "";
        try {
            Scanner scanner = new Scanner(Paths.get("./openai_key.txt"));
            key = scanner.next();
            scanner.close();
        } catch (IOException exception) { printMessage("Не удалось прочитать файл с ключом OpenAI"); }

        return key;
    }

    @GetMapping(value = "/api/openai/chat", params = "request")
    @ResponseBody
    public String getTextAnswerFromBot(@RequestParam String request) {

        List<ChatMessage> messagesList = new ArrayList();
        ChatMessage message = new ChatMessage("user", request);
        messagesList.add(message);
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(messagesList)
                .build();

        return service.createChatCompletion(completionRequest).getChoices().get(0).getMessage().getContent();
    }

    private void printMessage(String message) { System.out.println("[LightLMS - OpenAI]\t" + message); }
}
