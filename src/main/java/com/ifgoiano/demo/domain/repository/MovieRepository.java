package com.ifgoiano.demo.domain.repository;

import com.ifgoiano.demo.domain.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    public Optional<Movie> findMovieByTitle(String title);
}
