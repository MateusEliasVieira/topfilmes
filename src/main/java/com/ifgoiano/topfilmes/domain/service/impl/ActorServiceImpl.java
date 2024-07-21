package com.ifgoiano.topfilmes.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifgoiano.topfilmes.domain.domainException.BusinessRulesException;
import com.ifgoiano.topfilmes.domain.model.Actor;
import com.ifgoiano.topfilmes.domain.model.Movie;
import com.ifgoiano.topfilmes.domain.repository.ActorRepository;
import com.ifgoiano.topfilmes.domain.repository.MovieRepository;
import com.ifgoiano.topfilmes.domain.service.ActorService;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository repository;
    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = false)
    @Override
    public Actor add(Actor actor) {
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
    public Actor update(Actor actor) {
        return repository.save(actor);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteById(Long idActor) {
        try {
            searchById(idActor);
            repository.deleteById(idActor);
        } catch (BusinessRulesException businessRulesException) {
            throw new BusinessRulesException("Não existe ator com id " + idActor + " para ser deletado!");
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Actor searchById(Long idActor) {
        return repository.findById(idActor).orElseThrow(() -> new BusinessRulesException("Não existe ator com id " + idActor + "!"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Actor> listAll() {
        return repository.findAll();
    }

}
