package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.List;

public interface ListService {
    public List add(List list);

    public List update(List list);

    public java.util.List<List> listAll();

    public List searchById(Long idList);

    public void deleteById(Long idList);
}
