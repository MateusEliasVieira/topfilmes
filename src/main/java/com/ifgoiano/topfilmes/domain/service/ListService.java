package com.ifgoiano.topfilmes.domain.service;

import com.ifgoiano.topfilmes.api.dto.list.ListAddMovieRequestDTO;
import com.ifgoiano.topfilmes.api.dto.list.ListIDRequestDTO;
import com.ifgoiano.topfilmes.api.dto.list.ListRequestDTO;
import com.ifgoiano.topfilmes.domain.model.List;
import com.ifgoiano.topfilmes.domain.model.Movie;

public interface ListService {
    public List add(List list);
    public List addMovieToList(ListAddMovieRequestDTO listAddMovieRequestDTO);

    public List update(List list);

    public java.util.List<List> listAll();

    public List searchById(Long idList);

    public void deleteById(Long idList);
}
