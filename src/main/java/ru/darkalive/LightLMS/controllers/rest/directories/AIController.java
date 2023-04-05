package ru.darkalive.LightLMS.controllers.rest.directories;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AIController {

    OpenAiService service = new OpenAiService("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");

    @GetMapping(value = "/api/chat-bot", params = "request")
    @ResponseBody
    public String getTextAnswerFromBot(@RequestParam String request) {

        List<ChatMessage> messagesList = new ArrayList();
        ChatMessage message = new ChatMessage("system", request);
        messagesList.add(message);
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(messagesList)
                .build();

        return service.createChatCompletion(completionRequest).getChoices().get(0).getMessage().getContent();
    }
}
