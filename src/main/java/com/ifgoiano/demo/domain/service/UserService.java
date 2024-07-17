package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.User;

import java.util.List;

public interface UserService {

    public User criarNovoUsuario(User user);
    public void deletarUsuarioPorId(Long idUsuario);
    public User atualizarUsuarioPorId(User user);
    public User buscarUsuarioPorId(Long idUsuario);
    public List<User> listarTodosUsuarios();

}
