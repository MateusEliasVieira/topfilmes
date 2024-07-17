package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.Actor;

import java.util.List;

public interface ActorService {

    public Actor salvarAtor(Actor actor);

    public Actor atualizarAtor(Actor actor);

    public void deletarAtorPorId(Long idAtor);

    public Actor buscarAtorPorId(Long idAtor);

    public List<Actor> listarTodosAtores();


}
