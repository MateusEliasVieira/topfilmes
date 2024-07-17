package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.Session;

import java.util.List;

public interface SessionService {
    public Session add(Session session);

    public Session update(Session session);

    public List<Session> listAll();

    public Session searchById(Long idSession);

    public void deleteById(Long idSession);
}
