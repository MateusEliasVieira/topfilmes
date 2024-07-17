package com.ifgoiano.demo.api.dto.filme;

import com.ifgoiano.demo.domain.enums.Gender;
import com.ifgoiano.demo.domain.model.User;
import com.ifgoiano.demo.utils.PersonalizedResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmeRequestDTO {


    private Long idFilme;

    @NotBlank(message = PersonalizedResponse.FILME_ATR_TITULO_VAZIO)
    private String titulo;

    @NotBlank(message = PersonalizedResponse.FILME_ATR_DIRETOR_VAZIO)
    private String diretor;

    @Enumerated
    @NotNull(message = PersonalizedResponse.FILME_ATR_GENERO_NULO)
    private Gender gender;

    @NotBlank(message = PersonalizedResponse.FILME_ATR_SINOPSE_VAZIO)
    @Size(max = 280, message = PersonalizedResponse.FILME_ATR_SINOPSE_TAMANHO)
    private String sinopse;

    private Date lancamento;

    @NotNull(message = PersonalizedResponse.FILME_ATR_DURACAO_NULO)
    @Positive(message = PersonalizedResponse.FILME_ATR_DURACAO_POSITIVO)
    private int duracao;

    @NotNull(message = PersonalizedResponse.FILME_ATR_CLASSIFICACAO_NULO)
    private int classificacao;

    @NotBlank(message = PersonalizedResponse.FILME_ATR_DISTRIBUIDORA_VAZIO)
    private String distribuidora;

    // Informar json aninhado de usuario com id para cadastrar filme

    @NotNull
    private User user;

}
