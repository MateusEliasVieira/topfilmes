package com.ifgoiano.topfilmes.domain.repository;

import com.ifgoiano.topfilmes.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByCpf(String cpf);
}
