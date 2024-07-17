package com.ifgoiano.demo.domain.service.impl;

import com.ifgoiano.demo.domain.domainException.BusinessRulesException;
import com.ifgoiano.demo.domain.model.Movie;
import com.ifgoiano.demo.domain.repository.MovieRepository;
import com.ifgoiano.demo.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository repository;

    @Transactional(readOnly = false)
    @Override
    public Movie adicionarNovoFilme(Movie movie) {
        // Verificar se o filme não está cadastrado
        if (repository.findMovieByTitle(movie.getTitle()).isPresent()) {
            throw new BusinessRulesException("O filme " + movie.getTitle() + " já está cadastrado em nosso sistema!");
        } else {
            return repository.save(movie);
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void deletarFilmePorId(Long idFilme) {
        repository.deleteById(idFilme);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movie> listarTodosFilmes() {
        return repository.findAll();
    }

}
