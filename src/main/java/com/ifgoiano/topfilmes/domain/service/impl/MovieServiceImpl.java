package com.ifgoiano.topfilmes.domain.service.impl;

import com.ifgoiano.topfilmes.domain.domainException.BusinessRulesException;
import com.ifgoiano.topfilmes.domain.model.Movie;
import com.ifgoiano.topfilmes.domain.repository.MovieRepository;
import com.ifgoiano.topfilmes.domain.service.MovieService;
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
    public Movie add(Movie movie) {
        // Verificar se o filme não está cadastrado
        if (repository.findMovieByTitle(movie.getTitle()).isPresent()) {
            throw new BusinessRulesException("O filme " + movie.getTitle() + " já está cadastrado em nosso sistema!");
        } else {
            return repository.save(movie);
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteById(Long idMovie) {
        repository.deleteById(idMovie);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movie> listAll() {
        return repository.findAll();
    }

}
