package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.User;

import java.util.List;

public interface UserService {

    public User add(User user);
    public void deleteById(Long idUser);
    public User update(User user);
    public User searchById(Long idUser);
    public List<User> listAll();

}
