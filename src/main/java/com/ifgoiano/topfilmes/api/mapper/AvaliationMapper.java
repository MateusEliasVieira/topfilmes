package com.ifgoiano.topfilmes.api.mapper;

import com.ifgoiano.topfilmes.api.dto.avaliation.AvaliationRequestDTO;
import com.ifgoiano.topfilmes.api.dto.avaliation.AvaliationResponseDTO;
import com.ifgoiano.topfilmes.domain.model.Avaliation;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AvaliationMapper {

    public static Avaliation convertAvaliationRequestDTOToAvaliationEntity(AvaliationRequestDTO avaliationRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(avaliationRequestDTO, Avaliation.class);
    }

    public static AvaliationResponseDTO convertAvaliationEntityToAvaliationResponseDTO(Avaliation avaliation){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(avaliation, AvaliationResponseDTO.class);
    }

    public static List<AvaliationResponseDTO> convertListAvaliationEntityToListAvaliationResponseDTO(List<Avaliation> listAvaliation) {
        ModelMapper modelMapper = new ModelMapper();
        return listAvaliation.stream()
                .map(avaliation -> modelMapper.map(avaliation, AvaliationResponseDTO.class))
                .collect(Collectors.toList());
    }
    
    

}
