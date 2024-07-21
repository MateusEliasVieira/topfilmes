package com.ifgoiano.topfilmes.api.dto.avaliacao;

import com.ifgoiano.topfilmes.api.dto.usuario.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvaliationResponseDTO {

    private Long idAvaliation;
    private int score;
    private Date dateHour;

    // Relacionamentos
    private UserResponseDTO user;
}
