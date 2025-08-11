package br.com.lovizoto.apichatbot.services;

import br.com.lovizoto.apichatbot.repository.MessageLogRepository;
import br.com.lovizoto.commons.enums.Direction;
import br.com.lovizoto.commons.model.MessageLog;

public class MessageLogService {

    private final MessageLogRepository messageLogRepository;

    public MessageLogService(MessageLogRepository messageLogRepository) {
        this.messageLogRepository = messageLogRepository;
    }

    public void logMessage(String sessionId, String message, Direction direction){
        MessageLog messageLog = new MessageLog();
        messageLog.setSessionId(sessionId);
        messageLog.setMessage(message);
        messageLog.setDirection(direction);
        messageLogRepository.save(messageLog);
    }

}
