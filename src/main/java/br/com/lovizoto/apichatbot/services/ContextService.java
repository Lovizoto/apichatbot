package br.com.lovizoto.apichatbot.services;

import br.com.lovizoto.apichatbot.model.Context;
import br.com.lovizoto.apichatbot.repository.ContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ContextService {

    private final ContextRepository contextRepository;


    public ContextService(ContextRepository contextRepository) {
        this.contextRepository = contextRepository;
    }

    public Context findContextBySessionId(String sessionId) {
        return contextRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new RuntimeException("Context not found for session: " + sessionId)); //use a custom exception
    }

    public void createEmptyContext(String sessionId) {
        Context context = new Context();
        context.setSessionId(sessionId);
        context.setContextJson("{}"); //empty initial context
        contextRepository.save(context);
    }

    public void updateContext(Context context, String newContextJson) {
        context.setContextJson(newContextJson);
        context.setUpdatedAt(LocalDateTime.now());
        contextRepository.save(context);
    }


}
