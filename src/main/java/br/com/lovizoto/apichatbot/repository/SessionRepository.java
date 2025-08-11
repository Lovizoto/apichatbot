package br.com.lovizoto.apichatbot.repository;

import br.com.lovizoto.apichatbot.model.Session;
import br.com.lovizoto.commons.enums.SessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {

    Optional<Session> findByUserIdAndStatus(String userId, SessionStatus status);


//    @Modifying
//    @Query("UPDATE Session s SET s.lastActive = :now, s.status = :status WHERE s.id = :id")
//    void updateActivity(@Param("id") Long id, @Param("now") LocalDateTime now, @Param("status") SessionStatus status);

}
