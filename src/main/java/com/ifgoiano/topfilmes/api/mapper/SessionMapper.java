package com.ifgoiano.topfilmes.api.mapper;

import com.ifgoiano.topfilmes.api.dto.session.SessionRequestDTO;
import com.ifgoiano.topfilmes.api.dto.session.SessionResponseDTO;
import com.ifgoiano.topfilmes.domain.model.Session;
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
