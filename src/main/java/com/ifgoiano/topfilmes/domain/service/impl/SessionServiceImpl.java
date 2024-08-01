package com.ifgoiano.topfilmes.domain.service.impl;

import com.ifgoiano.topfilmes.domain.domainException.BusinessRulesException;
import com.ifgoiano.topfilmes.domain.model.Movie;
import com.ifgoiano.topfilmes.domain.model.Session;
import com.ifgoiano.topfilmes.domain.repository.MovieRepository;
import com.ifgoiano.topfilmes.domain.repository.SessionRepository;
import com.ifgoiano.topfilmes.domain.service.SessionService;
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
    private MovieRepository movieRepository;

    @Transactional(readOnly = false)
    @Override
    public Session add(Session session) {
        if(repository.findByCodSession(session.getCodSession()).isPresent())
            throw new BusinessRulesException("Já existe uma sessão com o código "+session.getCodSession());

        return repository.save(session);
    }

    @Transactional(readOnly = false)
    @Override
    public Session update(Session session) {
        Optional<Session> sessionFound = repository.findByCodSession(session.getCodSession());

        if(sessionFound.isPresent())
            if(sessionFound.get().getIdSession() != session.getIdSession())
                throw new BusinessRulesException("Já existe uma sessão com o código "+session.getCodSession());

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
