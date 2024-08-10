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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Movie add(Movie movie, MultipartFile cover, MultipartFile trailer) {
        // Verifica se a lista de atores está vazia
        if (movie.getActors().isEmpty()) {
            throw new BusinessRulesException("Informe os atores do filme!");
        }

        // Verifica se o usuário existe pelo ID
        userService.searchById(movie.getUser().getIdUser());

        // Verifica se o gênero do filme é válido
        Genders genders = new Genders();
        if (!genders.getListGender().contains(movie.getGender().toString())) {
            throw new BusinessRulesException("Gênero inválido!");
        }

        // Verifica se o filme já está cadastrado pelo título
        String movieTitle = movie.getTitle().toUpperCase().trim();
        if (repository.findMovieByTitle(movieTitle).isPresent()) {
            throw new BusinessRulesException("O filme " + movie.getTitle() + " já está cadastrado em nosso sistema!");
        }

        // Ajusta o título do filme para caixa alta e remove espaços em branco
        movie.setTitle(movieTitle);

        // Pega os bytes do video e imagem
        try {
            movie.setCover(cover.getBytes());
            movie.setTrailer(trailer.getBytes());
        } catch (IOException e) {
            throw new BusinessRulesException("Falha ao cadastrar filme!");
        }

        // Itera sobre os atores do filme para verificar ou criar novos registros
        List<Actor> updatedActors = new ArrayList<>();
        for (Actor actor : movie.getActors()) {
            // Define o nome do ator em caixa alta e remove espaços em branco
            actor.setName(actor.getName().toUpperCase().trim());

            Optional<Actor> actorFindById = Optional.empty();
            Optional<Actor> actorFindByName = Optional.empty();

            if (actor.getIdActor() != null) {
                // Se o ID do ator foi passado, busca o ator pelo ID
                actorFindById = actorService.searchById(actor.getIdActor());
            }

            if (actorFindById.isPresent()) {
                // verificar se o nome também é o mesmo
                if (actorFindById.get().getName().equalsIgnoreCase(actor.getName())) {
                    // Se encontrou pelo ID, utiliza o ator encontrado
                    updatedActors.add(actorFindById.get());
                } else {
                    // verificar no banco se existe algum ator com esse nome
                    actorFindByName = actorService.searchByName(actor.getName());

                    if (actorFindByName.isPresent()) {
                        updatedActors.add(actorFindByName.get());
                    } else {
                        actor.setIdActor(null);
                        Actor newActor = actorService.add(actor); // Michael caine deu erro aqui
                        updatedActors.add(newActor);
                    }
                }

            } else if (actorFindByName.isPresent()) {
                // Se encontrou pelo nome, utiliza o ator encontrado
                updatedActors.add(actorFindByName.get());
            } else {
                // Novo ator, persistir no banco de dados
                actor.setIdActor(null);
                Actor newActor = actorService.add(actor);
                updatedActors.add(newActor);
            }
        }

        // Atualiza a lista de atores do filme
        movie.setActors(updatedActors);

        // Salva o filme e retorna o resultado
        return repository.save(movie);
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
        return repository.findAllByOrderByLaunchDesc();
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
            throw new BusinessRulesException("Não foi encontrado o filme com id " + idMovie + "!");
        });

    }


    @Override
    public List<Movie> searchByStringInTitleMovie(String title) {
        return repository.findMovieByTitleLike(title.toUpperCase().trim()).orElseThrow(() -> {
            throw new BusinessRulesException("Não foi encontrado nenhum filme que contenha (" + title + ") em seu nome!");
        });
    }
}
