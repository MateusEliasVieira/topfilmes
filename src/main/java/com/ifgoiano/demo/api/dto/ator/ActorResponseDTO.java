package com.ifgoiano.demo.api.dto.ator;

import com.ifgoiano.demo.api.dto.filme.MovieResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorResponseDTO {
    private Long idAtor;
    private String nome;
    private Date dataNascimento;
    private List<MovieResponseDTO> filmes;
}
