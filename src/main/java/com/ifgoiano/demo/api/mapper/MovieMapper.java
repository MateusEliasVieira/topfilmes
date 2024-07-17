package com.ifgoiano.demo.api.mapper;

import com.ifgoiano.demo.api.dto.filme.FilmeRequestDTO;
import com.ifgoiano.demo.api.dto.filme.FilmeResponseDTO;
import com.ifgoiano.demo.domain.model.Movie;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MovieMapper {

    public static Movie converterFilmeRequestDTOParaFilmeEntidade(FilmeRequestDTO filmeRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(filmeRequestDTO, Movie.class);
    }

    public static FilmeResponseDTO converterFilmeEntidadeParaFilmeResponseDTO(Movie movie){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movie, FilmeResponseDTO.class);
    }

    public static List<FilmeResponseDTO> converterListaDeFilmeEntidadeParaListaDeFilmeResponseDTO(List<Movie> listaMovie) {
        ModelMapper modelMapper = new ModelMapper();
        return listaMovie.stream()
                .map(filme -> modelMapper.map(filme, FilmeResponseDTO.class))
                .collect(Collectors.toList());
    }


}
