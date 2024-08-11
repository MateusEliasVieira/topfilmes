package com.ifgoiano.topfilmes.domain.service.impl;

import com.ifgoiano.topfilmes.domain.domainException.BusinessRulesException;
import com.ifgoiano.topfilmes.domain.model.Cinema;
import com.ifgoiano.topfilmes.domain.model.Movie;
import com.ifgoiano.topfilmes.domain.model.Session;
import com.ifgoiano.topfilmes.domain.model.User;
import com.ifgoiano.topfilmes.domain.repository.CinemaRepository;
import com.ifgoiano.topfilmes.domain.repository.MovieRepository;
import com.ifgoiano.topfilmes.domain.repository.SessionRepository;
import com.ifgoiano.topfilmes.domain.service.CinemaService;
import com.ifgoiano.topfilmes.domain.service.MovieService;
import com.ifgoiano.topfilmes.domain.service.SessionService;
import com.ifgoiano.topfilmes.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private CinemaRepository cinemaRepository;

    @Transactional(readOnly = false)
    @Override
    public Session add(Session session) {

        User user = userService.searchById(session.getUser().getIdUser());
        Optional<Session> session_exist = repository.findByCodSession(session.getCodSession());

        if (session.getUser().getIdUser() == null)
            throw new BusinessRulesException("Informe o id do usuário!");

        if (session.getMovies().isEmpty())
            throw new BusinessRulesException("Informe o(s) filme(s) para esta sessão!");

        if (session.getCinemas().isEmpty())
            throw new BusinessRulesException("Informe o(s) cinema(a) que a sessão tem!");

        // Se existe a sessão, então adicionamos o cinema e o filme a ela
        if (session_exist.isPresent()) {
            // verificamos se quem esta fazendo essa adição é o mesmo usuário responsável
            if (session_exist.get().getUser().getIdUser() == session.getUser().getIdUser()) {
                // adiciona os cinemas para a sessão existente
                session.getCinemas().forEach((cinema) -> {
                    session_exist.get().getCinemas().forEach((cinema_exist) -> {
                        if (cinema_exist.getIdCinema() == cinema.getIdCinema()) {
                            throw new BusinessRulesException("O cinema " + cinema_exist.getName() + " já foi adicionado a esta sessão!");
                        }
                    });

                    cinemaRepository.findById(cinema.getIdCinema()) // se passar é porque existe o cinema
                            .orElseThrow(() -> {
                                throw new BusinessRulesException("Não existe o cinema com id " + cinema.getIdCinema());
                            });
                    session_exist.get().getCinemas().add(cinema);
                });
                // adiciona os filmes para sessão existente
                session.getMovies().forEach((movie) -> {
                    session_exist.get().getMovies().forEach((movie_exist) -> {
                        if (movie_exist.getIdMovie() == movie.getIdMovie()) {
                            throw new BusinessRulesException("O filme " + movie_exist.getTitle() + " já esta adicionado a essa sessão!");
                        }
                    });
                    movieService.searchById(movie.getIdMovie()); // se passar é porque existe o filme
                    session_exist.get().getMovies().add(movie);
                });

                // atualiza a sessão existente
                return repository.save(session_exist.get());
            } else {
                throw new BusinessRulesException("O usuário " + user.getName() + " não pode adicionar o filme e cinema a esta sessão, pois ela foi criada pelo usuário " + session_exist.get().getUser().getName() + "!");
            }
        } else {
            // verificar se o(s) filme(s) existe(m)
            session.getMovies().forEach((movie_exist) -> {
                movieService.searchById(movie_exist.getIdMovie()); // se passar é porque existe
            });

            // verificar se o(s) cinema(s) existe(m)
            session.getCinemas().forEach((cinema_exist) -> {
                cinemaRepository.findById(cinema_exist.getIdCinema())
                        .orElseThrow(() -> {
                            throw new BusinessRulesException("Não existe nenhum cinema com id " + cinema_exist.getIdCinema() + "!");
                        });
            });
        }

        // salva uma nova sessão
        Session session_saved = repository.save(session);
        return searchById(session_saved.getIdSession());
    }

    @Transactional(readOnly = false)
    @Override
    public Session update(Session session) {
        Optional<Session> sessionFound = repository.findByCodSession(session.getCodSession());

        if (sessionFound.isPresent())
            if (sessionFound.get().getIdSession() != session.getIdSession())
                throw new BusinessRulesException("Já existe uma sessão com o código " + session.getCodSession());

        return repository.save(session);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Session> listAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Session searchById(Long idSession) {
        return repository.findById(idSession).orElseThrow(() -> new BusinessRulesException("Não existe sessão com id " + idSession + "!"));

    }

    @Override
    public Session searchByCodSession(Long codSession) {
        return repository.findByCodSession(codSession).orElseThrow(() -> {
            throw new BusinessRulesException("Não foi encontrado nenhuma sessão com código " + codSession + "!");
        });
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteById(Long idSessao) {
        try {
            searchById(idSessao);
            repository.deleteById(idSessao);
        } catch (BusinessRulesException businessRulesException) {
            throw new BusinessRulesException("Não existe sessão com id " + idSessao + " para ser deletada!");
        }
    }

}
