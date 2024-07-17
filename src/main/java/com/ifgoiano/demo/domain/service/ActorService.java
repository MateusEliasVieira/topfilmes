package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.Actor;

import java.util.List;

public interface ActorService {

    public Actor add(Actor actor);

    public Actor update(Actor actor);

    public void deleteById(Long idActor);

    public Actor searchById(Long idActor);

    public List<Actor> listAll();


}
