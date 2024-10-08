package com.ifgoiano.topfilmes.api.dto.avaliation;

import com.ifgoiano.topfilmes.api.dto.movie.MovieIDRequestDTO;
import com.ifgoiano.topfilmes.api.dto.user.UserIDRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvaliationRequestDTO {

    private Long idAvaliation;

    @NotNull
    private int score;

    // Relacionamentos
    @NotNull
    private UserIDRequestDTO user; // Informa apenas o id do usuário de referência
    @NotNull
    private MovieIDRequestDTO movie; // Informa apenas o id do filme de referência

}
