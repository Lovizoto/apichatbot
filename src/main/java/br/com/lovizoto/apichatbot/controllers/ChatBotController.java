package br.com.lovizoto.apichatbot.controllers;

import br.com.lovizoto.apichatbot.model.Message;
import br.com.lovizoto.apichatbot.services.ChatBotService;
import br.com.lovizoto.commons.dto.MessageLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chatbot")
public class ChatBotController {


    private ChatBotService chatBotService;

    @PostMapping("/message")
    public ResponseEntity<MessageLog> sendMessage(@RequestBody MessageLog message) {
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}
