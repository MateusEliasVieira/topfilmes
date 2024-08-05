package com.ifgoiano.topfilmes.domain.service.impl;

import com.ifgoiano.topfilmes.domain.domainException.BusinessRulesException;
import com.ifgoiano.topfilmes.domain.model.Cinema;
import com.ifgoiano.topfilmes.domain.model.Session;
import com.ifgoiano.topfilmes.domain.repository.CinemaRepository;
import com.ifgoiano.topfilmes.domain.repository.SessionRepository;
import com.ifgoiano.topfilmes.domain.service.CinemaService;
import com.ifgoiano.topfilmes.domain.service.SessionService;
import com.ifgoiano.topfilmes.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository repository;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;

    @Transactional(readOnly = false)
    @Override
    public Cinema add(Cinema cinema) {
        Optional<Cinema> optionalCinema = repository.findByCnpj(cinema.getCnpj());

        if(cinema.getUser().getIdUser() == null)
            throw new BusinessRulesException("Informe o id do usuário!");

        userService.searchById(cinema.getUser().getIdUser()); // se passar, existe!

        if (optionalCinema.isEmpty()) {
            // pode salvar um novo
            return repository.save(cinema);
        } else {
            throw new BusinessRulesException("O cinema com cnpj " + cinema.getCnpj() + " já está cadastrado em nosso sistema!");
        }
    }

    @Transactional(readOnly = false)
    @Override
    public Cinema update(Cinema cinema) {
        if(cinema.getIdCinema() != null){
            searchById(cinema.getIdCinema()); // se passar, existe
            return repository.save(cinema);
        }else{
            throw new BusinessRulesException("Informe o cinema a ser atualizado!");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cinema> listAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Cinema searchById(Long idCinema) {
        return repository.findById(idCinema).orElseThrow(() -> new BusinessRulesException("Não existe cinema com id " + idCinema + "!"));
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteById(Long idCinema) {
        // Tenta encontrar o cinema pelo ID
        try {
            Cinema cinema = searchById(idCinema);

            // Remove o cinema das listas de cinemas em todas as sessões associadas
            cinema.getSessions().forEach(session -> {
                session.getCinemas().remove(cinema); // Remove a referência do cinema na sessão
            });

            // Limpa a lista de sessões do cinema
            cinema.getSessions().clear();

            // Salva as alterações feitas no cinema
            repository.save(cinema);

            // Deleta o cinema pelo ID
            repository.deleteById(idCinema);
        } catch (BusinessRulesException businessRulesException) {
            throw new BusinessRulesException("Não existe cinema com id " + idCinema + " para ser deletado!");
        }
    }


}
