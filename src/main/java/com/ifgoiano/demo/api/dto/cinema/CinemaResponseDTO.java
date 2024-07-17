package com.ifgoiano.demo.api.dto.cinema;

import com.ifgoiano.demo.api.dto.sessao.SessaoResponseDTO;

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
    private String nome;
    private String endereco;
    private String funcionamento;
    private int salas;
    private String cnpj;

    // Relacionamentos
    private List<SessaoResponseDTO> sessoes;

}
