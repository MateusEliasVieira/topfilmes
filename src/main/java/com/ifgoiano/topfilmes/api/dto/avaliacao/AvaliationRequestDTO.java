package com.ifgoiano.topfilmes.api.dto.avaliacao;

import com.ifgoiano.topfilmes.api.dto.filme.MovieRequestDTO;
import com.ifgoiano.topfilmes.api.dto.usuario.UserRequestDTO;
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

    @NotNull
    private int score;
    private Date dateHour = new Date();

    // Relacionamentos
    private UserRequestDTO user;
    private MovieRequestDTO movie;

}
