package com.ifgoiano.topfilmes.api.dto.filme;

import com.ifgoiano.topfilmes.api.dto.ator.ActorResponseDTO;
import com.ifgoiano.topfilmes.api.dto.avaliacao.AvaliationResponseDTO;
import com.ifgoiano.topfilmes.api.dto.comentario.CommentResponseDTO;
import com.ifgoiano.topfilmes.domain.enums.Gender;

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

    private Long idMovie;
    private String title;
    private String director;
    private Gender gender;
    private String sinopse;
    private Date launch;
    private int duration;
    private int classification;
    private String distributor;

    // Relacionamentos
    private List<AvaliationResponseDTO> avaliations;
    private List<CommentResponseDTO> comments;
    private List<ActorResponseDTO> actors;

}
