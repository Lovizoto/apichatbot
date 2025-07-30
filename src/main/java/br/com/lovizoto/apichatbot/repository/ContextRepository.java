package br.com.lovizoto.apichatbot.repository;

import br.com.lovizoto.apichatbot.model.Context;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContextRepository extends JpaRepository<Context, String> {
}
