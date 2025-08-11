package br.com.lovizoto.apichatbot.repository;

import br.com.lovizoto.commons.model.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageLogRepository extends JpaRepository<MessageLog,Long> {
}
