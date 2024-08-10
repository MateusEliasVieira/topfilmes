package com.ifgoiano.topfilmes.domain.repository;

import com.ifgoiano.topfilmes.domain.model.Avaliation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvaliationRepository extends JpaRepository<Avaliation, Long> {

    @Query("SELECT a FROM Avaliation a WHERE a.user.idUser = :usuario_id")
    public Optional<List<Avaliation>> findByUsuarioId(@Param("usuario_id") Long usuario_id);

    @Query("SELECT a FROM Avaliation a WHERE a.movie.idMovie = :idMovie")
    public Optional<List<Avaliation>> findAvaliationByMovie(@Param("idMovie") Long idMovie);
}
