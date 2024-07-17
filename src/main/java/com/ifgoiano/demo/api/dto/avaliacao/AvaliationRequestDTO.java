package com.ifgoiano.demo.api.dto.avaliacao;

import com.ifgoiano.demo.api.dto.filme.MovieRequestDTO;
import com.ifgoiano.demo.api.dto.usuario.UserRequestDTO;
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
    private int pontuacao;
    private Date dataHora = new Date();

    // Relacionamentos
    private UserRequestDTO usuario;
    private MovieRequestDTO filme;

}