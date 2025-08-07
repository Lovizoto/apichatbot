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

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Service
public class SessionService {


    private final SessionRepository sessionRepository;

    private final ContextRepository contextRepository;

    private final NlpService nlpService;

    public SessionService(SessionRepository sessionRepository, ContextRepository contextRepository, NlpService nlpService) {
        this.sessionRepository = sessionRepository;
        this.contextRepository = contextRepository;
        this.nlpService = nlpService;
    }

    public String createSession(String userId) {

        sessionRepository.findByUserIdAndStatus(userId, SessionStatus.ACTIVE)
                .ifPresent(session -> {
                    //close if inative and update
                    if (session.getLastActive().isBefore(LocalDateTime.now().minusHours(2))) {
                        session.setStatus(SessionStatus.CLOSED);
                        sessionRepository.save(session);
                    } else {
                        //throw exception here
                    }

                });

        //create a new session if not exist active session
        Session session = new Session();
        session.setUserId(userId);
        sessionRepository.save(session);

        //create a context
        Context context = new Context();
        context.setSessionId(session.getId());
        context.setContextJson("{}");
        contextRepository.save(context);


        return session.getId();
    }


    public String processMessage(String sessionId, String message) throws JsonProcessingException {

        Context context = contextRepository.findBySessionId(sessionId).orElseThrow(); //handle with exception

        String response = nlpService.processMessage(message, context.getContextJson());
        //context.updateContext(response)

        return response;

    }

//    public String processMessage(String sessionId, String message) throws JsonProcessingException {
//
//        Session session = sessionRepository.findById(sessionId).orElse(null); //exception
//
//        //Update Last Activity
//        session.setLastActive(LocalDateTime.now());
//        sessionRepository.save(session);
//
//        //Recover and update context
//        Context context = contextRepository.findBySessionId(session.getId()).orElseThrow(); //exception
//
//        Map<String, Object> contextMap = deserializeContext(context.getContextJson());
//
//        String response = generateResponse(message, contextMap);
//
//
//        context.setContextJson(serializeContext(contextMap));
//        context.setUpdatedAt(LocalDateTime.now());
//        contextRepository.save(context);
//
//        return response;
//
//    }
//
//    private Map<String, Object> deserializeContext(String json) throws JsonProcessingException {
//        return new ObjectMapper().readValue(json, new TypeReference<Map<String, Object>>() {});
//    }
//
//    private String serializeContext(Map<String, Object> map) throws JsonProcessingException {
//        return new ObjectMapper().writeValueAsString(map);
//    }


}
