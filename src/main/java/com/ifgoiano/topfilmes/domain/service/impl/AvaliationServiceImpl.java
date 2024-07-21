package com.ifgoiano.topfilmes.domain.service.impl;

import com.ifgoiano.topfilmes.domain.domainException.BusinessRulesException;
import com.ifgoiano.topfilmes.domain.model.Avaliation;
import com.ifgoiano.topfilmes.domain.model.User;
import com.ifgoiano.topfilmes.domain.repository.AvaliationRepository;
import com.ifgoiano.topfilmes.domain.repository.UserRepository;
import com.ifgoiano.topfilmes.domain.service.AvaliationService;
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
    public Avaliation add(Avaliation avaliation) {
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
    public Avaliation update(Avaliation avaliation) {
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
            searchById(idAvaliation);
            repository.deleteById(idAvaliation);
        } catch (BusinessRulesException businessRulesException) {
            throw new BusinessRulesException("Não existe avaliação com id " + idAvaliation + " para ser deletada!");
        }
    }
}
