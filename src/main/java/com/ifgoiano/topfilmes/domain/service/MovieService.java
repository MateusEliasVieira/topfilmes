package com.ifgoiano.topfilmes.domain.service;

import com.ifgoiano.topfilmes.domain.model.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {

    public Movie add(Movie movie, MultipartFile cover, MultipartFile trailer);
    public Movie update(Movie movie, MultipartFile cover, MultipartFile trailer);
    public Movie update(Movie movie);
    public void deleteById(Long idFilme);
    public List<Movie> listAll();
    public Movie searchByTitle(String title);
    public List<Movie> searchByGender(String gender);
    public Movie searchById(Long idMovie);
    public List<Movie> searchByStringInTitleMovie(String title);

}
