package com.ifgoiano.topfilmes.domain.repository;

import com.ifgoiano.topfilmes.domain.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
