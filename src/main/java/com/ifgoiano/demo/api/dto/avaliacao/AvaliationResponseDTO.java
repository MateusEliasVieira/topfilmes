package com.ifgoiano.demo.api.dto.avaliacao;

import com.ifgoiano.demo.api.dto.usuario.UserResponseDTO;
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

    private Long idAvaliacao;
    private int pontuacao;
    private Date dataHora;

    // Relacionamentos
    private UserResponseDTO usuario;
}
