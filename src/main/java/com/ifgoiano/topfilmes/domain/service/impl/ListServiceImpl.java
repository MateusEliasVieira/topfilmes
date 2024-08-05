package com.ifgoiano.topfilmes.domain.service.impl;

import com.ifgoiano.topfilmes.api.dto.list.ListAddMovieRequestDTO;
import com.ifgoiano.topfilmes.domain.domainException.BusinessRulesException;
import com.ifgoiano.topfilmes.domain.model.Movie;
import com.ifgoiano.topfilmes.domain.model.List;
import com.ifgoiano.topfilmes.domain.repository.MovieRepository;
import com.ifgoiano.topfilmes.domain.repository.ListRepository;
import com.ifgoiano.topfilmes.domain.service.ListService;
import com.ifgoiano.topfilmes.domain.service.MovieService;
import com.ifgoiano.topfilmes.domain.service.UserService;
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
    @Autowired
    private MovieService movieService;
    @Autowired
    private UserService userService;

    @Transactional(readOnly = false)
    @Override
    public List add(List list) {
        if (repository.findListByFkUser(list.getUser().getIdUser()).isPresent()) {
            throw new BusinessRulesException("O usuário informado já possui uma lista! Portanto, não é possível adicionar outra lista para ele!");
        } else {
            userService.searchById(list.getUser().getIdUser()); // se passar por essa linha, é porque encontrou.
            return repository.save(list);
        }
    }

    @Override
    public List addMovieToList(ListAddMovieRequestDTO listAddMovieRequestDTO) {
//        {
//            "idList": 1,
//                "addition": "2024-08-05T13:01:06.739Z",
//                "user": {
//            "idUser": 2
//        },
//            "movie": {
//            "idMovie": 2
//        }
//        }

        List list = searchById(listAddMovieRequestDTO.getIdList()); // se passar, existe!

        if(listAddMovieRequestDTO.getMovie().equals(null)) throw new BusinessRulesException("Informe o filme para ser adicionado a lista!");

        // verifica se o usuário passado é o mesmo usuário dono da lista
        if(list.getUser().getIdUser() != listAddMovieRequestDTO.getUser().getIdUser())
            throw new BusinessRulesException("Não é permitido o usuário "
                    +userService.searchById(listAddMovieRequestDTO.getUser().getIdUser()).getName()+
                    " adicionar filmes na lista do usuário "
                    +userService.searchById(list.getUser().getIdUser()).getName()+"!");

        // verifica se o filme já não foi adicionado a lista
        list.getMovies().forEach((movie)->{
            if (movie.getIdMovie() == listAddMovieRequestDTO.getMovie().getIdMovie()) throw new BusinessRulesException("Você já adicionou esse filme a lista!");
        });

        Movie movie = movieService.searchById(listAddMovieRequestDTO.getMovie().getIdMovie());
        list.getMovies().add(movie);
        movie.setList(java.util.List.of(list));

        return repository.save(list);

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
