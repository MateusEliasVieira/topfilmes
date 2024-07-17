package com.ifgoiano.demo.api.mapper;

import com.ifgoiano.demo.api.dto.lista.ListaRequestDTO;
import com.ifgoiano.demo.api.dto.lista.ListaResponseDTO;
import com.ifgoiano.demo.domain.model.List;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;

public class ListMapper {

    public static List converterListaRequestDTOEmListaEntidade(ListaRequestDTO listaRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(listaRequestDTO, List.class);
    }

    public static ListaResponseDTO converterListaEntidadeEmListaResponseDTO(List list){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(list, ListaResponseDTO.class);
    }

    public static java.util.List<ListaResponseDTO> converterListaDeListaEntidadeParaListaDeListaResponseDTO(java.util.List<List> listaList) {
        ModelMapper modelMapper = new ModelMapper();
        return listaList.stream()
                .map(lista -> modelMapper.map(lista, ListaResponseDTO.class))
                .collect(Collectors.toList());
    }

}
