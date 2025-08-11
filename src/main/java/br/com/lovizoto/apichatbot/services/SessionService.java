package br.com.lovizoto.apichatbot.services;

import br.com.lovizoto.apichatbot.model.Context;
import br.com.lovizoto.apichatbot.model.Session;
import br.com.lovizoto.apichatbot.repository.ContextRepository;
import br.com.lovizoto.apichatbot.repository.SessionRepository;
import br.com.lovizoto.commons.enums.SessionStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Service
public class SessionService {


    private final SessionRepository sessionRepository;
    private final ContextService contextService;

    public SessionService(SessionRepository sessionRepository, ContextService contextService) {
        this.sessionRepository = sessionRepository;
        this.contextService = contextService;
    }

    public String getOrCreateSession(String userId) {

        // Attempts to find an ACTIVE session for the user
        return sessionRepository.findByUserIdAndStatus(userId, SessionStatus.ACTIVE)
                .map(session -> {

                    if (session.getLastActive().isBefore(LocalDateTime.now().minusHours(2))) {
                        session.setStatus(SessionStatus.EXPIRED);
                        sessionRepository.save(session);
                        return createNewSession(userId);
                    }
                    return session.getId();

                })
                .orElseGet(() -> createNewSession(userId));

    }

    @Transactional
    public String createNewSession(String userId) {
        Session session = new Session();
        session.setUserId(userId);
        session.setStartedAt(LocalDateTime.now());
        session.setLastActive(LocalDateTime.now());
        session.setStatus(SessionStatus.ACTIVE);
        Session savedSession = sessionRepository.save(session);

        contextService.createEmptyContext(savedSession.getId());
        return savedSession.getId();
    }

    public void updateSessionActivity(String sessionId){
        Session session = sessionRepository.findById(sessionId).orElseThrow(); //add custom exception
        session.setLastActive(LocalDateTime.now());
        sessionRepository.save(session);
    }


}
