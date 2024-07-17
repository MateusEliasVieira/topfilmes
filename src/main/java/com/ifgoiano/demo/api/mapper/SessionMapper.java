package com.ifgoiano.demo.api.mapper;

import com.ifgoiano.demo.api.dto.sessao.SessaoRequestDTO;
import com.ifgoiano.demo.api.dto.sessao.SessaoResponseDTO;
import com.ifgoiano.demo.domain.model.Session;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class SessionMapper {

    public static Session converterSessaoRequestDTOEmSessaoEntidade(SessaoRequestDTO sessaoRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(sessaoRequestDTO, Session.class);
    }

    public static SessaoResponseDTO converterCinemaEntidadeEmCinemaResponseDTO(Session session) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(session, SessaoResponseDTO.class);
    }

    public static List<SessaoResponseDTO> converterListaDeSessaoEntidadeParaListaDeSessaoResponseDTO(List<Session> listaSession) {
        ModelMapper modelMapper = new ModelMapper();
        return listaSession.stream()
                .map(sessao -> modelMapper.map(sessao, SessaoResponseDTO.class))
                .collect(Collectors.toList());
    }


}
