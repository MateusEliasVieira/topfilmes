package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.Movie;

import java.util.List;

public interface MovieService {

    public Movie add(Movie movie);
    public void deleteById(Long idFilme);

    public List<Movie> listAll();

}
