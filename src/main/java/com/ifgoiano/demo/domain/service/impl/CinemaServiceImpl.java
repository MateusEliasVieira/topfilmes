package com.ifgoiano.demo.domain.service.impl;

import com.ifgoiano.demo.domain.domainException.BusinessRulesException;
import com.ifgoiano.demo.domain.model.Cinema;
import com.ifgoiano.demo.domain.model.Session;
import com.ifgoiano.demo.domain.repository.CinemaRepository;
import com.ifgoiano.demo.domain.repository.SessionRepository;
import com.ifgoiano.demo.domain.service.CinemaService;
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
    private SessionRepository sessionRepository;

    @Transactional(readOnly = false)
    @Override
    public Cinema add(Cinema cinema) {
        Optional<Cinema> optionalCinema = repository.findByCnpj(cinema.getCnpj());
        if (optionalCinema.isEmpty()) {
            Cinema cinemaSalvo = repository.save(cinema);
            List<Session> listaDeSession = new ArrayList<Session>();
            for (int i = 0; i < cinemaSalvo.getSessions().size(); i++) {
                listaDeSession.add(sessionRepository.findById(cinema.getSessions().get(i).getIdSession()).orElseThrow(() -> new BusinessRulesException("Não foi possível vincular uma sessão ao cinema, pois o id da sessão é inválido!")));
            }
            cinemaSalvo.setSessions(listaDeSession);
            return cinemaSalvo;
        } else {
            throw new BusinessRulesException("O cinema com cnpj " + cinema.getCnpj() + " já está cadastrado em nosso sistema!");
        }
    }

    @Transactional(readOnly = false)
    @Override
    public Cinema update(Cinema cinema) {
        return repository.save(cinema);
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
        try {
            searchById(idCinema);
            repository.deleteById(idCinema);
        } catch (BusinessRulesException businessRulesException) {
            throw new BusinessRulesException("Não existe cinema com id " + idCinema + " para ser deletado!");
        }
    }

}
