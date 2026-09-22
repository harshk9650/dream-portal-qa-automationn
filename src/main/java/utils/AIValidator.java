package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AIValidator {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static String classifyDreamType(String dreamName) throws Exception {
        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("OPENAI_API_KEY environment variable is not set.");
        }

        String prompt = "Classify the following dream as either 'Good' or 'Bad'. Respond with only one word: Good or Bad.\n\nDream: " + dreamName;

        String requestBody = "{\n" +
                "  \"model\": \"gpt-3.5-turbo\",\n" +
                "  \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}],\n" +
                "  \"temperature\": 0\n" +
                "}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonArray choices = jsonResponse.getAsJsonArray("choices");
            if (choices.size() > 0) {
                JsonObject firstChoice = choices.get(0).getAsJsonObject();
                JsonObject message = firstChoice.getAsJsonObject("message");
                String content = message.get("content").getAsString().trim();
                return content;
            }
        } else {
            throw new RuntimeException("Failed to get response from AI API. Status code: " + response.statusCode() + ", Response: " + response.body());
        }
        return "Unknown";
    }
}
