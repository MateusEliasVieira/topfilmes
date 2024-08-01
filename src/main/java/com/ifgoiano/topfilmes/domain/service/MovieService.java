package com.ifgoiano.topfilmes.domain.service;

import com.ifgoiano.topfilmes.domain.model.Movie;

import java.util.List;

public interface MovieService {

    public Movie add(Movie movie);
    public void deleteById(Long idFilme);
    public List<Movie> listAll();
    public Movie searchById(Long idMovie);

}
