package br.com.lovizoto.apichatbot.services;

import br.com.lovizoto.apichatbot.model.Session;
import br.com.lovizoto.apichatbot.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {


    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }


    public Optional<Session> findSessionById(String sessionId) {
        return sessionRepository.findById(sessionId);
    }



}
