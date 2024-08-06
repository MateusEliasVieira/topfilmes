package com.ifgoiano.topfilmes.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifgoiano.topfilmes.domain.domainException.BusinessRulesException;
import com.ifgoiano.topfilmes.domain.model.Actor;
import com.ifgoiano.topfilmes.domain.repository.ActorRepository;
import com.ifgoiano.topfilmes.domain.service.ActorService;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository repository;

    @Transactional(readOnly = false)
    @Override
    public Actor add(Actor actor) {
        return repository.save(actor);
    }

    @Transactional(readOnly = false)
    @Override
    public Actor update(Actor actor) {

        if(actor.getIdActor() == null)
            throw new BusinessRulesException("Informe o id do filme para atualizar!");

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
        return repository.findById(idActor).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Actor> listAll() {
        return repository.findAll();
    }

}
