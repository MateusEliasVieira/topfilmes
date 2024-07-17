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
public class MovieRequestDTO {


    private Long idMovie;

    @NotBlank(message = PersonalizedResponse.FILME_ATR_TITULO_VAZIO)
    private String title;

    @NotBlank(message = PersonalizedResponse.FILME_ATR_DIRETOR_VAZIO)
    private String director;

    @Enumerated
    @NotNull(message = PersonalizedResponse.FILME_ATR_GENERO_NULO)
    private Gender gender;

    @NotBlank(message = PersonalizedResponse.FILME_ATR_SINOPSE_VAZIO)
    @Size(max = 280, message = PersonalizedResponse.FILME_ATR_SINOPSE_TAMANHO)
    private String sinopse;

    private Date launch;

    @NotNull(message = PersonalizedResponse.FILME_ATR_DURACAO_NULO)
    @Positive(message = PersonalizedResponse.FILME_ATR_DURACAO_POSITIVO)
    private int duration;

    @NotNull(message = PersonalizedResponse.FILME_ATR_CLASSIFICACAO_NULO)
    private int classification;

    @NotBlank(message = PersonalizedResponse.FILME_ATR_DISTRIBUIDORA_VAZIO)
    private String distributor;

    // Informar json aninhado de usuario com id para cadastrar filme

    @NotNull
    private User user;

}
