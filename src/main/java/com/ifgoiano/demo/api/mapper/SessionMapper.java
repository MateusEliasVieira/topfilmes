package com.ifgoiano.demo.api.mapper;

import com.ifgoiano.demo.api.dto.sessao.SessionRequestDTO;
import com.ifgoiano.demo.api.dto.sessao.SessionResponseDTO;
import com.ifgoiano.demo.domain.model.Session;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class SessionMapper {

    public static Session converterSessaoRequestDTOEmSessaoEntidade(SessionRequestDTO sessionRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(sessionRequestDTO, Session.class);
    }

    public static SessionResponseDTO converterCinemaEntidadeEmCinemaResponseDTO(Session session) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(session, SessionResponseDTO.class);
    }

    public static List<SessionResponseDTO> converterListaDeSessaoEntidadeParaListaDeSessaoResponseDTO(List<Session> listaSession) {
        ModelMapper modelMapper = new ModelMapper();
        return listaSession.stream()
                .map(sessao -> modelMapper.map(sessao, SessionResponseDTO.class))
                .collect(Collectors.toList());
    }


}
