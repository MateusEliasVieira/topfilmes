package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.Cinema;

import java.util.List;

public interface CinemaService {
    public Cinema add(Cinema cinema);

    public Cinema update(Cinema cinema);

    public List<Cinema> listAll();

    public Cinema searchById(Long idCinema);

    public void deleteById(Long idCinema);
}
