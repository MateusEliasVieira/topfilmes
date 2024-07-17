package com.ifgoiano.demo.api.mapper;

import com.ifgoiano.demo.api.dto.avaliacao.AvaliacaoRequestDTO;
import com.ifgoiano.demo.api.dto.avaliacao.AvaliacaoResponseDTO;
import com.ifgoiano.demo.domain.model.Avaliation;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AvaliationMapper {

    public static Avaliation converterAvaliacaoRequestDTOEmAvaliacaoEntidade(AvaliacaoRequestDTO avaliacaoRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(avaliacaoRequestDTO, Avaliation.class);
    }

    public static AvaliacaoResponseDTO converterAvaliacaoEntidadeEmAvaliacaoResponseDTO(Avaliation avaliation){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(avaliation, AvaliacaoResponseDTO.class);
    }

    public static List<AvaliacaoResponseDTO> converterListaDeAvaliacaoEntidadeParaListaDeAvaliacaoResponseDTO(List<Avaliation> listaAvaliation) {
        ModelMapper modelMapper = new ModelMapper();
        return listaAvaliation.stream()
                .map(avaliacao -> modelMapper.map(avaliacao, AvaliacaoResponseDTO.class))
                .collect(Collectors.toList());
    }
    
    

}
