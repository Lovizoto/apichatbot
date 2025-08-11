package br.com.lovizoto.apichatbot.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

//Nlp: Natural Language Process
@Service
public class NlpService {

    public String processMessage(String message, String contextJson) throws JsonProcessingException {

        Map<String, Object> contextMap = deserializeContext(contextJson);

        String response = generateResponse(message, contextMap);

        //update context here???

        return response;
    }

    private Map<String, Object> deserializeContext(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, new TypeReference<Map<String, Object>>() {});
    }

    private String serializeContext(Map<String, Object> map) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(map);
    }


    //create a provider by rest api of any chatbot (openai, gemini, etc) - use the method generate response




}
