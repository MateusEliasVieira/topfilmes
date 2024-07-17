package com.ifgoiano.demo.api.dto.cinema;

import com.ifgoiano.demo.api.dto.sessao.SessaoRequestDTO;
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
    private String nome;
    @NotBlank
    private String endereco;
    @NotBlank
    private String funcionamento;
    @NotNull
    private int salas;
    @CNPJ
    private String cnpj;

    private List<SessaoRequestDTO> sessoes;
}
