package com.ifgoiano.topfilmes.domain.service;

import com.ifgoiano.topfilmes.domain.enums.Gender;
import com.ifgoiano.topfilmes.domain.model.Movie;

import java.util.List;

public interface MovieService {

    public Movie add(Movie movie);
    public Movie update(Movie movie);
    public void deleteById(Long idFilme);
    public List<Movie> listAll();
    public List<Movie> searchByGender(Gender gender);
    public Movie searchById(Long idMovie);

}
