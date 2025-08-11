package br.com.lovizoto.apichatbot.services;

import br.com.lovizoto.apichatbot.model.Context;
import br.com.lovizoto.commons.dto.MessageRequest;
import br.com.lovizoto.commons.dto.MessageResponse;
import br.com.lovizoto.commons.dto.SessionRequest;
import br.com.lovizoto.commons.dto.SessionResponse;
import br.com.lovizoto.commons.enums.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatbotService {


    private final SessionService sessionService;
    private final NlpService nlpService;
    private final ContextService contextService;
    private final MessageLogService messageLogService;

    public ChatbotService(SessionService sessionService, NlpService nlpService, ContextService contextService, MessageLogService messageLogService) {
        this.sessionService = sessionService;
        this.nlpService = nlpService;
        this.contextService = contextService;
        this.messageLogService = messageLogService;
    }

    @Transactional
    public SessionResponse createNewSession(SessionRequest request) {
        String sessionId = sessionService.getOrCreateSession(request.userId());
        return new SessionResponse(sessionId);
    }

    @Transactional
    public MessageResponse processMessage(String sessionId, MessageRequest request) {
        messageLogService.logMessage(sessionId, request.message(), Direction.INCOMING);

        Context context = contextService.findContextBySessionId(sessionId);

        NlpService.NlpResult nlpResult = nlpService.processMessage(request.message(), context.getContextJson());

        contextService.updateContext(context, nlpResult.getNewContextJson());

        sessionService.updateSessionActivity(sessionId);

        messageLogService.logMessage(sessionId, nlpResult.getResponse(), Direction.OUTGOING);

        return new MessageResponse(nlpResult.getResponse());
    }

}
