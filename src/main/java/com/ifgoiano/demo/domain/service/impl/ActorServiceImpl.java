package com.ifgoiano.demo.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifgoiano.demo.domain.domainException.BusinessRulesException;
import com.ifgoiano.demo.domain.model.Actor;
import com.ifgoiano.demo.domain.model.Movie;
import com.ifgoiano.demo.domain.repository.ActorRepository;
import com.ifgoiano.demo.domain.repository.MovieRepository;
import com.ifgoiano.demo.domain.service.ActorService;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository repository;
    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = false)
    @Override
    public Actor salvarAtor(Actor actor) {
        Actor actorSalvo = repository.save(actor);
        List<Movie> listaDeMovies = new ArrayList<Movie>();
        for (int i = 0; i < actor.getMovies().size(); i++) {
            listaDeMovies.add(movieRepository.findById(actorSalvo.getMovies().get(i).getIdMovie()).orElseThrow(() -> new BusinessRulesException("Não foi possível vincular um filme ao ator, pois o id do filme é inválido!")));
        }
        ;
        actorSalvo.setMovies(listaDeMovies);
        return actorSalvo;
    }

    @Transactional(readOnly = false)
    @Override
    public Actor atualizarAtor(Actor actor) {
        return repository.save(actor);
    }

    @Transactional(readOnly = false)
    @Override
    public void deletarAtorPorId(Long idAtor) {
        try {
            buscarAtorPorId(idAtor);
            repository.deleteById(idAtor);
        } catch (BusinessRulesException businessRulesException) {
            throw new BusinessRulesException("Não existe ator com id " + idAtor + " para ser deletado!");
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Actor buscarAtorPorId(Long idAtor) {
        return repository.findById(idAtor).orElseThrow(() -> new BusinessRulesException("Não existe ator com id " + idAtor + "!"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Actor> listarTodosAtores() {
        return repository.findAll();
    }

}
