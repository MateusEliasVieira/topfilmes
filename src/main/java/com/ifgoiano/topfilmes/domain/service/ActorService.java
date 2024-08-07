package com.ifgoiano.topfilmes.domain.service;

import com.ifgoiano.topfilmes.domain.model.Actor;
import com.ifgoiano.topfilmes.domain.model.Movie;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    public Actor add(Actor actor);

    public Actor update(Actor actor);

    public void deleteById(Long idActor);

    public Optional<Actor> searchById(Long idActor);
    public Optional<Actor> searchByName(String name);

    public List<Actor> listAll();



}
