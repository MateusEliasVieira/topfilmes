package com.ifgoiano.topfilmes.domain.service.impl;

import com.ifgoiano.topfilmes.api.dto.list.ListAddMovieRequestDTO;
import com.ifgoiano.topfilmes.api.dto.list.ListRemoveMovieRequestDTO;
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
import java.util.Iterator;
import java.util.stream.IntStream;

@Service
public class ListServiceImpl implements ListService {

    @Autowired
    private ListRepository repository;
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
    public List push(ListAddMovieRequestDTO listAddMovieRequestDTO) {

        // verifica se o id da lista existe
        if(listAddMovieRequestDTO.getIdList() == null) throw new BusinessRulesException("Informe a lista!");

        // verifica se a lista existe
        List list = searchById(listAddMovieRequestDTO.getIdList()); // se passar, existe!

        // verifica se foi informado o filme
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

        // busca o filme informado
        Movie movie = movieService.searchById(listAddMovieRequestDTO.getMovie().getIdMovie());
        // adiciona o filme a lista
        list.getMovies().add(movie);
        // adiciona a lista ao filme
        movie.setList(java.util.List.of(list));

        // salva e retorna
        return repository.save(list);

    }

    @Override
    public List pop(ListRemoveMovieRequestDTO listRemoveMovieRequestDTO) {
        if (listRemoveMovieRequestDTO.getIdList() == null) {
            throw new BusinessRulesException("Informe a lista!");
        }

        List list = searchById(listRemoveMovieRequestDTO.getIdList());

        Long movieIdToRemove = listRemoveMovieRequestDTO.getMovie().getIdMovie();

        // Use o Iterator para evitar exceções de índice fora do intervalo
        Iterator<Movie> movieIterator = list.getMovies().iterator();

        boolean movieFound = false; // Flag para verificar se o filme foi encontrado

        while (movieIterator.hasNext()) {
            Movie movie = movieIterator.next();

            if (movie.getIdMovie().equals(movieIdToRemove)) {
                // Achei o filme para remover
                movie.getList().remove(list); // Retira a referência do filme com a lista
                movieIterator.remove(); // Remove o filme da lista
                movieFound = true;
                break; // Sai do loop após encontrar e remover o filme
            }
        }

        if (!movieFound) {
            throw new BusinessRulesException("Filme não encontrado na lista especificada.");
        }

        repository.save(list); // Salva a lista atualizada

        return list; // Retorna a lista modificada
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

            List list = searchById(idLista);

            list.setUser(null); // limpa o usuário da lista

            list.getMovies().forEach((movie)->{ // limpa a lista relacionada aos filmes
                movie.getList().remove(list);
            });

            list.getMovies().clear(); // limpa a lista de filmes da lista

            repository.save(list);

            repository.deleteById(idLista);
        } catch (BusinessRulesException businessRulesException) {
            throw new BusinessRulesException("Não existe lista com id " + idLista + " para ser deletada!");
        }
    }

}
