package com.ifgoiano.topfilmes.api.dto.cinema;

import com.ifgoiano.topfilmes.api.dto.session.SessionIDRequestDTO;
import com.ifgoiano.topfilmes.api.dto.user.UserIDRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CinemaRequestDTO {

    private Long idCinema;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String operation;
    @NotNull
    private int rooms;
    @CNPJ
    private String cnpj;

    @NotNull
    private UserIDRequestDTO user; // Informa apenas o id do usuário de referência
}
