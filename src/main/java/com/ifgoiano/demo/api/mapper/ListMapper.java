package com.ifgoiano.demo.api.mapper;

import com.ifgoiano.demo.api.dto.lista.ListRequestDTO;
import com.ifgoiano.demo.api.dto.lista.ListResponseDTO;
import com.ifgoiano.demo.domain.model.List;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;

public class ListMapper {

    public static List converterListaRequestDTOEmListaEntidade(ListRequestDTO listRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(listRequestDTO, List.class);
    }

    public static ListResponseDTO converterListaEntidadeEmListaResponseDTO(List list){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(list, ListResponseDTO.class);
    }

    public static java.util.List<ListResponseDTO> converterListaDeListaEntidadeParaListaDeListaResponseDTO(java.util.List<List> listaList) {
        ModelMapper modelMapper = new ModelMapper();
        return listaList.stream()
                .map(lista -> modelMapper.map(lista, ListResponseDTO.class))
                .collect(Collectors.toList());
    }

}
