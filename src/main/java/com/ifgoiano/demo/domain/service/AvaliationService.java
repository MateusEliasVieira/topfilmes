package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.Avaliation;

import java.util.List;

public interface AvaliationService {

    public Avaliation salvarAvaliacao(Avaliation avaliation);

    public Avaliation atualizarAvaliacao(Avaliation avaliation);

    public List<Avaliation> listarTodasAvaliacoes();

    public Avaliation buscarAvaliacaoPorId(Long idAvaliacao);

    public void deletarAvaliacaoPorId(Long idAvaliacao);

}
