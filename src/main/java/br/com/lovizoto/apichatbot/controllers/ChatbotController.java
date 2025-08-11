package br.com.lovizoto.apichatbot.controllers;

import br.com.lovizoto.apichatbot.services.ChatbotService;
import br.com.lovizoto.commons.dto.MessageRequest;
import br.com.lovizoto.commons.dto.MessageResponse;
import br.com.lovizoto.commons.dto.SessionRequest;
import br.com.lovizoto.commons.dto.SessionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatbot")
public class ChatbotController {


    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatBotService) {
        this.chatbotService = chatBotService;
    }

    @PostMapping("/sessions")
    public ResponseEntity<SessionResponse> createSession(@RequestBody SessionRequest request) {
        SessionResponse response = chatbotService.createNewSession(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/sessions/{sessionId}/messages")
    public ResponseEntity<MessageResponse> handleMessage(@PathVariable String sessionId,
                                                         @RequestBody MessageRequest request) throws JsonProcessingException {
        MessageResponse response = chatbotService.processMessage(sessionId, request);
        return ResponseEntity.ok(response);
    }

}


