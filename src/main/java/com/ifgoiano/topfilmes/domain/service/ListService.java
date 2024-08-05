package com.ifgoiano.topfilmes.domain.service;

import com.ifgoiano.topfilmes.api.dto.list.ListAddMovieRequestDTO;
import com.ifgoiano.topfilmes.api.dto.list.ListRemoveMovieRequestDTO;
import com.ifgoiano.topfilmes.domain.model.List;

public interface ListService {
    public List add(List list);
    public List push(ListAddMovieRequestDTO listAddMovieRequestDTO);

    public List pop(ListRemoveMovieRequestDTO listRemoveMovieRequestDTO);

    public List update(List list);

    public java.util.List<List> listAll();

    public List searchById(Long idList);

    public void deleteById(Long idList);
}
