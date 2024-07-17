package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.Session;

import java.util.List;

public interface SessionService {
    public Session salvarSessao(Session session);

    public Session atualizarSessao(Session session);

    public List<Session> listarTodasSessoes();

    public Session buscarSessaoPorId(Long idSessao);

    public void deletarSessaoPorId(Long idSessao);
}
