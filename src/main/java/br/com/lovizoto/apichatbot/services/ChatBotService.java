package br.com.lovizoto.apichatbot.services;

import br.com.lovizoto.commons.dto.MessageLog;
import br.com.lovizoto.commons.enums.Direction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatBotService {

    public MessageLog handleMessage(MessageLog messageLog) {
        MessageLog response = new MessageLog();
        response.setSessionId(messageLog.getSessionId());
        response.setParsed_content("You can get it!");
        response.setParsed_content("You can get it!");
        response.setDirection(Direction.OUTGOING);
        response.setSource("chatbot");
        response.setTimestamp(LocalDateTime.now());
        return response;
    }


}
