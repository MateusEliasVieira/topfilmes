package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.Movie;

import java.util.List;

public interface MovieService {

    public Movie adicionarNovoFilme(Movie movie);
    public void deletarFilmePorId(Long idFilme);

    public List<Movie> listarTodosFilmes();

}
