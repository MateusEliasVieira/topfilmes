package com.ifgoiano.demo.api.dto.filme;

import com.ifgoiano.demo.api.dto.ator.ActorResponseDTO;
import com.ifgoiano.demo.api.dto.avaliacao.AvaliationResponseDTO;
import com.ifgoiano.demo.api.dto.comentario.CommentResponseDTO;
import com.ifgoiano.demo.domain.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDTO {

    private Long idFilme;
    private String titulo;
    private String diretor;
    private Gender gender;
    private String sinopse;
    private Date lancamento;
    private int duracao;
    private int classificacao;
    private String distribuidora;

    // Relacionamentos
    private List<AvaliationResponseDTO> avaliacoes;
    private List<CommentResponseDTO> comentarios;
    private List<ActorResponseDTO> atores;

}
