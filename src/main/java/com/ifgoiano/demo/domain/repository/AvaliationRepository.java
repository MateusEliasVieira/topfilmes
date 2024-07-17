package com.ifgoiano.demo.domain.repository;

import com.ifgoiano.demo.domain.model.Avaliation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvaliationRepository extends JpaRepository<Avaliation, Long> {

    @Query("SELECT a FROM Avaliation a WHERE a.user.idUser = :usuario_id")
    public Optional<Avaliation> findByUsuarioId(@Param("usuario_id") Long usuario_id);
}
