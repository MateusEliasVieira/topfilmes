package com.ifgoiano.topfilmes.domain.service.impl;

import com.ifgoiano.topfilmes.domain.domainException.BusinessRulesException;
import com.ifgoiano.topfilmes.domain.enums.Gender;
import com.ifgoiano.topfilmes.domain.model.Actor;
import com.ifgoiano.topfilmes.domain.model.Movie;
import com.ifgoiano.topfilmes.domain.repository.MovieRepository;
import com.ifgoiano.topfilmes.domain.service.ActorService;
import com.ifgoiano.topfilmes.domain.service.MovieService;
import com.ifgoiano.topfilmes.domain.service.UserService;
import com.ifgoiano.topfilmes.utils.Genders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private ActorService actorService;


    @Transactional(readOnly = false)
    @Override
    public Movie add(Movie movie) {

        if (movie.getActors().isEmpty())
            throw new BusinessRulesException("Informe os atores do filme!");

        userService.searchById(movie.getUser().getIdUser());

        Genders genders = new Genders();
        if (!genders.getListGender().contains(movie.getGender().toString())) {
            throw new BusinessRulesException("Gênero inválido!");
        }

        if (repository.findMovieByTitle(movie.getTitle().toUpperCase().trim()).isPresent()) {
            throw new BusinessRulesException("O filme " + movie.getTitle() + " já está cadastrado em nosso sistema!");
        }

        movie.setTitle(movie.getTitle().toUpperCase().trim());

        // Salva o filme
        return repository.save(movie);

        // está salvando, mas devo concertar o erro de logica ao salvar o ator com o filme, pois esta criando atores repetidos no banco de dados
    }



    @Override
    public Movie update(Movie movie) {

        userService.searchById(movie.getUser().getIdUser()); // se passar, existe o usuário

        Optional<Movie> movieFound = repository.findMovieByTitle(movie.getTitle().toUpperCase().trim());

        if (movieFound.isPresent())
            if (movieFound.get().getIdMovie() != movie.getIdMovie())
                throw new BusinessRulesException("Já existe outro filme cadastrado com o nome " + movie.getTitle());

        if (movie.getIdMovie() == null)
            throw new BusinessRulesException("Selecione o filme a ser atualizado!");

        movie.setTitle(movie.getTitle().toUpperCase().trim());
        return repository.save(movie);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteById(Long idMovie) {
        searchById(idMovie);
        repository.deleteById(idMovie);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movie> listAll() {
        return repository.findAll();
    }

    @Override
    public Movie searchByTitle(String title) {
        return repository.findMovieByTitle(title.toUpperCase().trim()).orElseThrow(() -> {
            throw new BusinessRulesException("Não foi encontrado nenhum filme com nome " + title + "!");
        });
    }

    @Override
    public List<Movie> searchByGender(String gender) {
        Gender gender_converted = null;
        try {
            gender_converted = Gender.valueOf(gender.toUpperCase().trim());
        } catch (Exception E) {
            throw new BusinessRulesException("Gênero inválido!");
        }

        if (gender_converted == null)
            throw new BusinessRulesException("Informe o gênero do filme para realizar a conculta!");

        return repository.findMovieByGender(gender_converted).orElseThrow(() -> {
            throw new BusinessRulesException("Ops, não temos filmes com esse gênero em nossa base de dados! :(");
        });

    }

    @Transactional(readOnly = true)
    @Override
    public Movie searchById(Long idMovie) {

        if (idMovie == null)
            throw new BusinessRulesException("Informe o id do filme para realizar a conculta!");

        return repository.findById(idMovie).orElseThrow(() -> {
            throw new BusinessRulesException("Não foi encontrado o filme!");
        });

    }

    @Override
    public List<Movie> searchByStringInTitleMovie(String title) {
        return repository.findMovieByTitleLike(title.toUpperCase().trim()).orElseThrow(() -> {
            throw new BusinessRulesException("Não foi encontrado nenhum filme que contenha (" + title + ") em seu nome!");
        });
    }
}
