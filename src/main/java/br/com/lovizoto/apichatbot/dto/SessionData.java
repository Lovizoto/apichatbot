package br.com.lovizoto.apichatbot.dto;

import br.com.lovizoto.commons.enums.SessionStatus;

import java.time.LocalDateTime;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SessionData {

    private String userId;
    private SessionStatus sessionStatus;
    private LocalDateTime createdAt;
    private Queue<String> messagesQueue = new ConcurrentLinkedQueue<>();
    private boolean isCreating;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Queue<String> getMessagesQueue() {
        return messagesQueue;
    }

    public void setMessagesQueue(Queue<String> messagesQueue) {
        this.messagesQueue = messagesQueue;
    }

    public boolean isCreating() {
        return isCreating;
    }

    public void setCreating(boolean creating) {
        isCreating = creating;
    }
}
