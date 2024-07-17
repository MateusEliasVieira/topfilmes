package com.ifgoiano.demo.domain.repository;

import com.ifgoiano.demo.domain.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
