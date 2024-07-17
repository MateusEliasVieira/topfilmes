package com.ifgoiano.demo.api.dto.endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {

    private Long idAddress;
    private String street;
    private String number;
    private String neighborhood;;
    private String cep;
    private String city;
    private String uf;

}
