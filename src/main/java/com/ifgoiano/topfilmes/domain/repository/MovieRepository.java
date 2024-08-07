package com.ifgoiano.topfilmes.domain.repository;

import com.ifgoiano.topfilmes.domain.enums.Gender;
import com.ifgoiano.topfilmes.domain.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    public Optional<Movie> findMovieByTitle(String title);

    public Optional<List<Movie>> findMovieByGender(Gender gender);

    @Query("SELECT m FROM Movie m WHERE m.title LIKE CONCAT('%', :title, '%')")
    public Optional<List<Movie>> findMovieByTitleLike(@Param("title") String title);

    public List<Movie> findAllByOrderByLaunchDesc();


}
