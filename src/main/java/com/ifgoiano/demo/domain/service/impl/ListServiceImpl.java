package com.ifgoiano.demo.domain.service.impl;

import com.ifgoiano.demo.domain.domainException.BusinessRulesException;
import com.ifgoiano.demo.domain.model.Movie;
import com.ifgoiano.demo.domain.model.List;
import com.ifgoiano.demo.domain.repository.MovieRepository;
import com.ifgoiano.demo.domain.repository.ListRepository;
import com.ifgoiano.demo.domain.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ListServiceImpl implements ListService {

    @Autowired
    private ListRepository repository;
    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = false)
    @Override
    public List add(List list) {
        List listSalva = repository.save(list);
        java.util.List<Movie> movies = new ArrayList<Movie>();
        for (int i = 0; i < listSalva.getMovies().size(); i++) {
            movies.add(movieRepository.findById(listSalva.getMovies().get(i).getIdMovie())
                    .orElseThrow(() -> new BusinessRulesException("Você tentou adicionar um filme a sua lista com id inválido!")));
        }
        listSalva.setMovies(movies);
        return listSalva;
    }

    @Transactional(readOnly = false)
    @Override
    public List update(List list) {
        return repository.save(list);
    }

    @Transactional(readOnly = true)
    @Override
    public java.util.List<List> listAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List searchById(Long idLista) {
        return repository.findById(idLista).orElseThrow(() -> new BusinessRulesException("Não existe lista com id " + idLista + "!"));
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteById(Long idLista) {
        try {
            searchById(idLista);
            repository.deleteById(idLista);
        } catch (BusinessRulesException businessRulesException) {
            throw new BusinessRulesException("Não existe lista com id " + idLista + " para ser deletada!");
        }
    }

}
