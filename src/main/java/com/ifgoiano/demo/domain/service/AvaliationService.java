package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.Avaliation;

import java.util.List;

public interface AvaliationService {

    public Avaliation add(Avaliation avaliation);

    public Avaliation update(Avaliation avaliation);

    public List<Avaliation> listAll();

    public Avaliation searchById(Long idAvaliation);

    public void deleteById(Long idAvaliation);

}
