package com.ifgoiano.topfilmes.api.mapper;

import com.ifgoiano.topfilmes.api.dto.session.SessionRequestDTO;
import com.ifgoiano.topfilmes.api.dto.session.SessionResponseDTO;
import com.ifgoiano.topfilmes.domain.model.Session;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class SessionMapper {

    public static Session convertSessionRequestDTOToSessionEntity(SessionRequestDTO sessionRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(sessionRequestDTO, Session.class);
    }

    public static SessionResponseDTO convertSessionEntityToSessionResponseDTO(Session session) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(session, SessionResponseDTO.class);
    }

    public static List<SessionResponseDTO> convertListSessionEntityToListSessionResponseDTO(List<Session> listSession) {
        ModelMapper modelMapper = new ModelMapper();
        return listSession.stream()
                .map(session -> modelMapper.map(session, SessionResponseDTO.class))
                .collect(Collectors.toList());
    }


}
