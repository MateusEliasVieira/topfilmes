package com.ifgoiano.demo.domain.service.impl;

import com.ifgoiano.demo.domain.domainException.BusinessRulesException;
import com.ifgoiano.demo.domain.model.Movie;
import com.ifgoiano.demo.domain.model.Session;
import com.ifgoiano.demo.domain.repository.MovieRepository;
import com.ifgoiano.demo.domain.repository.SessionRepository;
import com.ifgoiano.demo.domain.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository repository;
    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = false)
    @Override
    public Session salvarSessao(Session session) {
        Session sessionSalva = repository.save(session);
        List<Movie> listaDeMovies = new ArrayList<Movie>();
        for (int i = 0; i < sessionSalva.getMovies().size(); i++) {
            listaDeMovies.add(movieRepository.findById(session.getMovies().get(i).getIdMovie()).orElseThrow(() -> new BusinessRulesException("Não foi possível adicionar um filme na lista de filmes, pois o id desse filme é inválido!")));
        }
        sessionSalva.setMovies(listaDeMovies);
        return sessionSalva;
    }

    @Transactional(readOnly = false)
    @Override
    public Session atualizarSessao(Session session) {
        return repository.save(session);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Session> listarTodasSessoes() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Session buscarSessaoPorId(Long idSessao) {
        return repository.findById(idSessao).orElseThrow(() -> new BusinessRulesException("Não existe sessão com id " + idSessao + "!"));

    }

    @Transactional(readOnly = false)
    @Override
    public void deletarSessaoPorId(Long idSessao) {
        try {
            buscarSessaoPorId(idSessao);
            repository.deleteById(idSessao);
        } catch (BusinessRulesException businessRulesException) {
            throw new BusinessRulesException("Não existe sessão com id " + idSessao + " para ser deletada!");
        }
    }

}
