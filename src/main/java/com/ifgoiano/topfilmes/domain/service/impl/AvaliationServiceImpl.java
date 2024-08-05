package com.ifgoiano.topfilmes.domain.service.impl;

import com.ifgoiano.topfilmes.domain.domainException.BusinessRulesException;
import com.ifgoiano.topfilmes.domain.model.Avaliation;
import com.ifgoiano.topfilmes.domain.model.Movie;
import com.ifgoiano.topfilmes.domain.model.User;
import com.ifgoiano.topfilmes.domain.repository.AvaliationRepository;
import com.ifgoiano.topfilmes.domain.repository.UserRepository;
import com.ifgoiano.topfilmes.domain.service.AvaliationService;
import com.ifgoiano.topfilmes.domain.service.MovieService;
import com.ifgoiano.topfilmes.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvaliationServiceImpl implements AvaliationService {

    @Autowired
    private AvaliationRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieService movieService;

    @Transactional(readOnly = false)
    @Override
    public Avaliation add(Avaliation avaliation) {


        if (avaliation.getUser().getIdUser() == null)
            throw new BusinessRulesException("Informe o usuário!");

        User user = userService.searchById(avaliation.getUser().getIdUser()); // se passar, existe!


        if(avaliation.getMovie().getIdMovie() == null)
            throw new BusinessRulesException("Selecione o filme!");

        Movie movie = movieService.searchById(avaliation.getMovie().getIdMovie()); // se passar, existe!


        if(avaliation.getScore() > 5 || avaliation.getScore() < 0)
            throw new BusinessRulesException("A pontuação deve ser entre 0 a 5 estrelas!");


        // busca avaliação pelo fk id_usuario da tabela avaliação
        if(repository.findByUsuarioId(user.getIdUser()).isPresent())
            throw new BusinessRulesException("O usuário "+user.getName()+" já fez uma avaliação do filme "+movie.getTitle()+"!");

        avaliation.setMovie(movie);
        avaliation.setUser(user);

        Avaliation avaliation_saved = repository.save(avaliation);

        user.getAvaliations().add(avaliation_saved);
        movie.getAvaliations().add(avaliation_saved);

        userService.update(user);
        movieService.update(movie);

        return avaliation_saved;

    }

    @Transactional(readOnly = false)
    @Override
    public Avaliation update(Avaliation avaliation) {

        if(avaliation.getIdAvaliation() == null)
            throw new BusinessRulesException("Informe a avaliação!");

        searchById(avaliation.getIdAvaliation()); // se passar, existe

        return repository.save(avaliation);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Avaliation> listAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Avaliation searchById(Long idAvaliation) {
        return repository.findById(idAvaliation).orElseThrow(() -> new BusinessRulesException("Não existe avaliação com id " + idAvaliation + "!"));
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteById(Long idAvaliation) {
        try {

            Avaliation avaliation = searchById(idAvaliation);

            avaliation.setUser(null);
            avaliation.setMovie(null);

            update(avaliation);

            repository.deleteById(idAvaliation);
        } catch (BusinessRulesException businessRulesException) {
            throw new BusinessRulesException("Não existe avaliação com id " + idAvaliation + " para ser deletada!");
        }
    }
}
