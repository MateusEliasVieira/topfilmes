package com.ifgoiano.topfilmes.domain.service;

import com.ifgoiano.topfilmes.domain.model.Session;

import java.util.List;

public interface SessionService {
    public Session add(Session session);

    public Session update(Session session);

    public List<Session> listAll();

    public Session searchById(Long idSession);
    public Session searchByCodSession(Long codSession);

    public void deleteById(Long idSession);
}
