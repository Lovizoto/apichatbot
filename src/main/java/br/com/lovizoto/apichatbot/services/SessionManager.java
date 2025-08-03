package br.com.lovizoto.apichatbot.services;

import br.com.lovizoto.apichatbot.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class SessionManager {

    private final SessionRepository sessionRepository;
    private final Duration timeout = Duration.ofMinutes(5); //Here you can set the duration of a session

    @Autowired
    public SessionManager(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }


}
