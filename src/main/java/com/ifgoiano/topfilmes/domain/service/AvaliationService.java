package com.ifgoiano.topfilmes.domain.service;

import com.ifgoiano.topfilmes.domain.model.Avaliation;

import java.util.List;

public interface AvaliationService {

    public Avaliation add(Avaliation avaliation);

    public Avaliation update(Avaliation avaliation);

    public List<Avaliation> listAll();
    public List<Avaliation> listAvaliationByMovie(Long idMovie);

    public Avaliation searchById(Long idAvaliation);

    public void deleteById(Long idAvaliation);

}
