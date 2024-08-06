package com.ifgoiano.topfilmes.domain.repository;

import com.ifgoiano.topfilmes.domain.enums.Gender;
import com.ifgoiano.topfilmes.domain.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    public Optional<Movie> findMovieByTitle(String title);

    public Optional<List<Movie>> findMovieByGender(Gender gender);
}
