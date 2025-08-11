package br.com.lovizoto.apichatbot.services;

import br.com.lovizoto.apichatbot.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

//Nlp: Natural Language Process
@Service
public class NlpService {

    private final JsonUtil jsonUtil;

    public NlpService(JsonUtil jsonUtil) {
        this.jsonUtil = jsonUtil;
    }

    public NlpResult processMessage(String message, String currentContextJson) {

        Map<String, Object> contextMap = jsonUtil.deserializeContext(currentContextJson);

        // Logic for calling the NLP provider (e.g., Gemini, OpenAI)
        // The provider would receive the 'message' and the 'contextMap'
        String responseFromNlp = "Message processed based in " + message; //future updates here calling an NLP

        contextMap.put("last_user_message", message);
        contextMap.put("last_bot_response", responseFromNlp);
        contextMap.put("conversation_turns", (int) contextMap.getOrDefault("conversation_turns", 0) + 1);

        String newContextJson = jsonUtil.serialize(contextMap);

        return new NlpResult(responseFromNlp, newContextJson);

    }


    public static class NlpResult {
        private final String response;
        private final String newContextJson;

        public NlpResult(String response, String newContextJson) {
            this.response = response;
            this.newContextJson = newContextJson;
        }

        public String getResponse() {
            return response;
        }

        public String getNewContextJson() {
            return newContextJson;
        }
    }

}
