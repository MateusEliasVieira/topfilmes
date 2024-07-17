package com.ifgoiano.demo.api.dto.cinema;

import com.ifgoiano.demo.api.dto.sessao.SessionRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CinemaRequestDTO {

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

    private List<SessionRequestDTO> sessions;
}
