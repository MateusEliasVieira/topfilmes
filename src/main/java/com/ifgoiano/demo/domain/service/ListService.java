package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.List;

public interface ListService {
    public List salvarLista(List list);

    public List atualizarLista(List list);

    public java.util.List<List> listarTodasListas();

    public List buscarListaPorId(Long idLista);

    public void deletarListaPorId(Long idLista);
}
