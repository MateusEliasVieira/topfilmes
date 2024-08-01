package com.ifgoiano.topfilmes.api.mapper;

import com.ifgoiano.topfilmes.api.dto.list.ListRequestDTO;
import com.ifgoiano.topfilmes.api.dto.list.ListResponseDTO;
import com.ifgoiano.topfilmes.domain.model.List;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;

public class ListMapper {

    public static List convertListRequestDTOToListEntity(ListRequestDTO listRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(listRequestDTO, List.class);
    }

    public static ListResponseDTO convertListEntityToListResponseDTO(List list){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(list, ListResponseDTO.class);
    }

    public static java.util.List<ListResponseDTO> convertListOfListEntityToListOfListResponseDTO(java.util.List<List> listList) {
        ModelMapper modelMapper = new ModelMapper();
        return listList.stream()
                .map(list -> modelMapper.map(list, ListResponseDTO.class))
                .collect(Collectors.toList());
    }

}
