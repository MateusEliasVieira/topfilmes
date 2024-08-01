package com.ifgoiano.topfilmes.domain.service.impl;

import com.ifgoiano.topfilmes.domain.domainException.BusinessRulesException;
import com.ifgoiano.topfilmes.domain.model.User;
import com.ifgoiano.topfilmes.domain.repository.UserRepository;
import com.ifgoiano.topfilmes.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = false)
    @Override
    public User add(User user) {
        // Verificar se já existe esse usuário, se não existir então salva, caso contrário gera mensagem de aviso
        // Existe o usuario para atualizar
        Optional<User> userFound = repository.findByCpf(user.getCpf());

        if(userFound.isPresent())
            throw new BusinessRulesException("O CPF "+user.getCpf()+", pertence a outro usuário já cadastrado!");

        userFound = repository.findByEmail(user.getEmail());

        if(userFound.isPresent())
            throw new BusinessRulesException("O email "+user.getEmail()+", pertence a outro usuário já cadastrado!");

        userFound = repository.findByUser(user.getUser());

        if(userFound.isPresent())
            throw new BusinessRulesException("O nome de usuário "+user.getUser()+", pertence a outro usuário já cadastrado!");

        return repository.save(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteById(Long idUser) {
        if (repository.findById(idUser).isPresent()) {
            // Existe o usuario para deletar
            repository.deleteById(idUser);
        } else {
            // Não existe o usuário para atualizar
            throw new BusinessRulesException("Não existe usuário com id " + idUser);
        }
    }

    @Transactional(readOnly = false)
    @Override
    public User update(User user) {
        if (repository.findById(user.getIdUser()).isPresent()) {
            // Existe o usuario para atualizar
            Optional<User> userFound = repository.findByCpf(user.getCpf());

            if(userFound.isPresent())
                if(userFound.get().getIdUser() != user.getIdUser())
                    throw new BusinessRulesException("O CPF "+user.getCpf()+", pertence a outro usuário já cadastrado!");

            userFound = repository.findByEmail(user.getEmail());

            if(userFound.isPresent())
                if(userFound.get().getIdUser() != user.getIdUser())
                    throw new BusinessRulesException("O email "+user.getEmail()+", pertence a outro usuário já cadastrado!");

            userFound = repository.findByUser(user.getUser());

            if(userFound.isPresent())
                if(userFound.get().getIdUser() != user.getIdUser())
                    throw new BusinessRulesException("O nome de usuário "+user.getUser()+", pertence a outro usuário já cadastrado!");

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
