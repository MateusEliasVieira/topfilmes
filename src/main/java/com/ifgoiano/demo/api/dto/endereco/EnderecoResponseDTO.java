package com.ifgoiano.demo.api.dto.endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponseDTO {

    private Long idEndereco;
    private String rua;
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;

}
