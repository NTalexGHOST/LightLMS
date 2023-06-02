package ru.darkalive.LightLMS.controllers.rest.directories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.image.Image;
import com.theokanning.openai.service.OpenAiService;
import okhttp3.OkHttpClient;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Retrofit;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.theokanning.openai.service.OpenAiService.*;

@RestController
public class RestAIController {

    OpenAiService service = createOpenAiService(getOpenAiKey());

    private String getOpenAiKey() {

        String key = "";
        try {
            Scanner scanner = new Scanner(Paths.get("./openai_key.txt"));
            key = scanner.next();
            scanner.close();
        } catch (IOException exception) { printMessage("Не удалось прочитать файл с ключом OpenAI"); }

        return key;
    }

    private OpenAiService createOpenAiService(String key) {

        ObjectMapper mapper = defaultObjectMapper();
        OkHttpClient client = defaultClient(key, Duration.ofSeconds(60))
                .newBuilder()
                .build();
        Retrofit retrofit = defaultRetrofit(client, mapper);

        OpenAiApi api = retrofit.create(OpenAiApi.class);
        return new OpenAiService(api);
    }

    @GetMapping(value = "/api/openai/chat", params = "request")
    @ResponseBody
    public String getTextAnswerFromBot(@RequestParam String request) {

        List<ChatMessage> messagesList = new ArrayList<>();
        ChatMessage message = new ChatMessage("user", request);
        messagesList.add(message);
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(messagesList)
                .build();

        return service.createChatCompletion(completionRequest).getChoices().get(0).getMessage().getContent();
    }

    @GetMapping(value = "/api/openai/img", params = "request")
    @ResponseBody
    public String getImageUrlFromBot(@RequestParam String request) {

        CreateImageRequest imageRequest = CreateImageRequest.builder()
                .prompt(request)
                .n(1)
                .size("1024x1024")
                .build();

        return service.createImage(imageRequest).getData().get(0).getUrl();
    }

    private void printMessage(String message) { System.out.println("[LightLMS - OpenAI]\t" + message); }
}
