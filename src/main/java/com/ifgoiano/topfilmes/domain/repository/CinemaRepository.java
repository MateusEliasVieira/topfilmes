package com.ifgoiano.topfilmes.domain.repository;

import com.ifgoiano.topfilmes.domain.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    public Optional<Cinema> findByCnpj(String cnpj);
}
