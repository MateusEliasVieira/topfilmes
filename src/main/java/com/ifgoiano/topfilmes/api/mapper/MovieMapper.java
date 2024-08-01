package com.ifgoiano.topfilmes.api.mapper;

import com.ifgoiano.topfilmes.api.dto.movie.MovieRequestDTO;
import com.ifgoiano.topfilmes.api.dto.movie.MovieResponseDTO;
import com.ifgoiano.topfilmes.domain.model.Movie;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MovieMapper {

    public static Movie convertMovieRequestDTOToMovieEntity(MovieRequestDTO movieRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movieRequestDTO, Movie.class);
    }


    public static MovieResponseDTO convertMovieEntityToMovieResponseDTO(Movie movie){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movie, MovieResponseDTO.class);
    }

    public static List<MovieResponseDTO> convertListMovieEntityToListMovieResponseDTO(List<Movie> listMovie) {
        ModelMapper modelMapper = new ModelMapper();
        return listMovie.stream()
                .map(movie -> modelMapper.map(movie, MovieResponseDTO.class))
                .collect(Collectors.toList());
    }


}
