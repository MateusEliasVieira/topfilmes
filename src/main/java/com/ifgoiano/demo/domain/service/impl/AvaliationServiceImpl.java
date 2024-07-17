package com.ifgoiano.demo.domain.service.impl;

import com.ifgoiano.demo.domain.domainException.BusinessRulesException;
import com.ifgoiano.demo.domain.model.Avaliation;
import com.ifgoiano.demo.domain.model.User;
import com.ifgoiano.demo.domain.repository.AvaliationRepository;
import com.ifgoiano.demo.domain.repository.UserRepository;
import com.ifgoiano.demo.domain.service.AvaliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvaliationServiceImpl implements AvaliationService {

    @Autowired
    private AvaliationRepository repository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = false)
    @Override
    public Avaliation salvarAvaliacao(Avaliation avaliation) {
        if (repository.findByUsuarioId(avaliation.getUser().getIdUser()).isEmpty()) {
            Avaliation avaliationSaved = repository.save(avaliation);
            User user = userRepository.findById(avaliationSaved.getUser().getIdUser()).orElseThrow(() -> new BusinessRulesException("Não foi possível vincular o usuário para a avaliação, pois o id do usuário é inválido!"));
            avaliationSaved.setUser(user);
            return avaliationSaved;
        } else {
            throw new BusinessRulesException("O usuário de id " + avaliation.getUser().getIdUser() + " já realizou uma avaliação sobre o filme de id " + avaliation.getMovie().getIdMovie());
        }
    }

    @Transactional(readOnly = false)
    @Override
    public Avaliation atualizarAvaliacao(Avaliation avaliation) {
        return repository.save(avaliation);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Avaliation> listarTodasAvaliacoes() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Avaliation buscarAvaliacaoPorId(Long idAvaliacao) {
        return repository.findById(idAvaliacao).orElseThrow(() -> new BusinessRulesException("Não existe avaliação com id " + idAvaliacao + "!"));
    }

    @Transactional(readOnly = false)
    @Override
    public void deletarAvaliacaoPorId(Long idAvaliacao) {
        try {
            buscarAvaliacaoPorId(idAvaliacao);
            repository.deleteById(idAvaliacao);
        } catch (BusinessRulesException businessRulesException) {
            throw new BusinessRulesException("Não existe avaliação com id " + idAvaliacao + " para ser deletada!");
        }
    }
}
