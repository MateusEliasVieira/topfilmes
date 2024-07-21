package com.ifgoiano.topfilmes.domain.service.impl;

import com.ifgoiano.topfilmes.domain.domainException.BusinessRulesException;
import com.ifgoiano.topfilmes.domain.model.User;
import com.ifgoiano.topfilmes.domain.repository.UserRepository;
import com.ifgoiano.topfilmes.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = false)
    @Override
    public User add(User user) {
        // Verificar se já existe esse usuário, se não existir então salva, caso contrário gera mensagem de aviso
        if (repository.findByCpf(user.getCpf()).isPresent()) {
            // Existe
            throw new BusinessRulesException("O usuário com cpf " + user.getCpf() + " já está cadastrado em nosso sistema!");
        } else {
            return repository.save(user);
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteById(Long idUser) {
        repository.deleteById(idUser);
    }

    @Transactional(readOnly = false)
    @Override
    public User update(User user) {
        if (repository.findById(user.getIdUser()).isPresent()) {
            // Existe o usuario para atualizar
            return repository.save(user);
        } else {
            // Não existe o usuário para atualizar
            throw new BusinessRulesException("Não existe usuário com id " + user.getIdUser());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User searchById(Long idUser) {
        return repository.findById(idUser).orElseThrow(() -> new BusinessRulesException("Não existe usuário com id " + idUser));
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listAll() {
        return repository.findAll();
    }

}
