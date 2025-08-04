package br.com.lovizoto.apichatbot.services;

import br.com.lovizoto.apichatbot.dto.SessionData;
import br.com.lovizoto.apichatbot.model.Session;
import br.com.lovizoto.apichatbot.repository.SessionRepository;
import br.com.lovizoto.commons.dto.FirstContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionManager {

    private final SessionRepository sessionRepository;
    private final Duration timeout = Duration.ofMinutes(5); //Here you can set the duration of a session

    //Cache in-memory
    private final ConcurrentHashMap<String, SessionData> sessions = new ConcurrentHashMap<>();

    @Autowired
    public SessionManager(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public synchronized void handleMessage(FirstContactDto firstContactDto) {

        String userId = firstContactDto.getUserId();

        SessionData session = sessions.computeIfAbsent(userId, id -> {
            Optional<Session> existing = sessionRepository.findById(id);
            if (existing.isPresent()) {
                return mapToSessionData(existing.get());
            } else {
                SessionData sessionData = new SessionData();
                sessionData.setUserId(id);
                return sessionData;
            }
        });

    }

    private SessionData mapToSessionData(Session session) {



    }





}
