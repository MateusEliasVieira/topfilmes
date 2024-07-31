package com.ifgoiano.topfilmes.api.mapper;

import com.ifgoiano.topfilmes.api.dto.avaliation.AvaliationRequestDTO;
import com.ifgoiano.topfilmes.api.dto.avaliation.AvaliationResponseDTO;
import com.ifgoiano.topfilmes.domain.model.Avaliation;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AvaliationMapper {

    public static Avaliation converterAvaliacaoRequestDTOEmAvaliacaoEntidade(AvaliationRequestDTO avaliationRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(avaliationRequestDTO, Avaliation.class);
    }

    public static AvaliationResponseDTO converterAvaliacaoEntidadeEmAvaliacaoResponseDTO(Avaliation avaliation){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(avaliation, AvaliationResponseDTO.class);
    }

    public static List<AvaliationResponseDTO> converterListaDeAvaliacaoEntidadeParaListaDeAvaliacaoResponseDTO(List<Avaliation> listaAvaliation) {
        ModelMapper modelMapper = new ModelMapper();
        return listaAvaliation.stream()
                .map(avaliacao -> modelMapper.map(avaliacao, AvaliationResponseDTO.class))
                .collect(Collectors.toList());
    }
    
    

}
