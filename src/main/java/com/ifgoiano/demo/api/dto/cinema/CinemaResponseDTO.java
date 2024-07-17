package com.ifgoiano.demo.api.dto.cinema;

import com.ifgoiano.demo.api.dto.sessao.SessionResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CinemaResponseDTO {

    private Long idCinema;
    private String name;
    private String address;
    private String operation;
    private int rooms;
    private String cnpj;

    // Relacionamentos
    private List<SessionResponseDTO> sessions;

}
