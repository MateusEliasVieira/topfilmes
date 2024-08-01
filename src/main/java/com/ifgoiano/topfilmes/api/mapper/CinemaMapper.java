package com.ifgoiano.topfilmes.api.mapper;

import com.ifgoiano.topfilmes.api.dto.cinema.CinemaRequestDTO;
import com.ifgoiano.topfilmes.api.dto.cinema.CinemaResponseDTO;
import com.ifgoiano.topfilmes.domain.model.Cinema;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CinemaMapper {

    public static Cinema convertCinemaRequestDTOToCinemaEntity(CinemaRequestDTO cinemaRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cinemaRequestDTO, Cinema.class);
    }

    public static CinemaResponseDTO convertCinemaEntityToCinemaResponseDTO(Cinema cinema) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cinema, CinemaResponseDTO.class);
    }

    public static List<CinemaResponseDTO> convertListCinemaEntityToListCinemaResponseDTO(List<Cinema> listCinema) {
        ModelMapper modelMapper = new ModelMapper();
        return listCinema.stream()
                .map(cinema -> modelMapper.map(cinema, CinemaResponseDTO.class))
                .collect(Collectors.toList());
    }
}
