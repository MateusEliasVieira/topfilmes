package com.ifgoiano.demo.api.mapper;

import com.ifgoiano.demo.api.dto.filme.MovieRequestDTO;
import com.ifgoiano.demo.api.dto.filme.MovieResponseDTO;
import com.ifgoiano.demo.domain.model.Movie;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MovieMapper {

    public static Movie converterFilmeRequestDTOParaFilmeEntidade(MovieRequestDTO movieRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movieRequestDTO, Movie.class);
    }

    public static MovieResponseDTO converterFilmeEntidadeParaFilmeResponseDTO(Movie movie){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movie, MovieResponseDTO.class);
    }

    public static List<MovieResponseDTO> converterListaDeFilmeEntidadeParaListaDeFilmeResponseDTO(List<Movie> listaMovie) {
        ModelMapper modelMapper = new ModelMapper();
        return listaMovie.stream()
                .map(filme -> modelMapper.map(filme, MovieResponseDTO.class))
                .collect(Collectors.toList());
    }


}
