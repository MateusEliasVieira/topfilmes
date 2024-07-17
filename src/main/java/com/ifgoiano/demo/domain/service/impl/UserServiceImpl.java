package com.ifgoiano.demo.domain.service.impl;

import com.ifgoiano.demo.domain.domainException.BusinessRulesException;
import com.ifgoiano.demo.domain.model.User;
import com.ifgoiano.demo.domain.repository.UserRepository;
import com.ifgoiano.demo.domain.service.UserService;
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
    public User criarNovoUsuario(User user) {
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
    public void deletarUsuarioPorId(Long idUsuario) {
        repository.deleteById(idUsuario);
    }

    @Transactional(readOnly = false)
    @Override
    public User atualizarUsuarioPorId(User user) {
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
    public User buscarUsuarioPorId(Long idUsuario) {
        return repository.findById(idUsuario).orElseThrow(() -> new BusinessRulesException("Não existe usuário com id " + idUsuario));
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listarTodosUsuarios() {
        return repository.findAll();
    }

}
