package com.ifgoiano.topfilmes.domain.service.impl;

import com.ifgoiano.topfilmes.domain.domainException.BusinessRulesException;
import com.ifgoiano.topfilmes.domain.enums.Gender;
import com.ifgoiano.topfilmes.domain.model.Movie;
import com.ifgoiano.topfilmes.domain.repository.MovieRepository;
import com.ifgoiano.topfilmes.domain.service.MovieService;
import com.ifgoiano.topfilmes.domain.service.UserService;
import com.ifgoiano.topfilmes.utils.Genders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository repository;
    @Autowired
    private UserService userService;

    @Transactional(readOnly = false)
    @Override
    public Movie add(Movie movie) {

        userService.searchById(movie.getUser().getIdUser()); // se passar, existe o usuário

        // verificar se o genero é válido
        Genders genders = new Genders();
        if(!genders.getListGender().contains(movie.getGender().toString()))
            throw new BusinessRulesException("Gênero inválido!");

        // Verificar se o filme não está cadastrado
        if (repository.findMovieByTitle(movie.getTitle().toUpperCase().trim()).isPresent()) {
            throw new BusinessRulesException("O filme " + movie.getTitle() + " já está cadastrado em nosso sistema!");
        } else {
            movie.setTitle(movie.getTitle().toUpperCase().trim());
            return repository.save(movie);
        }
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
    public List<Movie> searchByGender(Gender gender) {

        if (gender == null)
            throw new BusinessRulesException("Informe o gênero do filme para realizar a conculta!");

        return repository.findMovieByGender(gender).orElseThrow(() -> {
            throw new BusinessRulesException("Ops, não temos filmes com esse gênero em nossa base de dados! :(");
        });

    }

    @Transactional(readOnly = true)
    @Override
    public Movie searchById(Long idMovie) {

        if (idMovie == null)
            throw new BusinessRulesException("Informe o id do filme para realizar a conculta!");

        return repository.findById(idMovie).orElseThrow(() -> {
            throw new BusinessRulesException("Não foi encontrado o filme com o id " + idMovie);
        });

    }
}
