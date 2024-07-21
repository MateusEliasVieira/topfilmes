package com.ifgoiano.topfilmes.api.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {

    @NotBlank
    private String street;
    private String number;
    @NotBlank
    private String neighborhood;
    @NotNull
    @Size(min = 9, max = 9)
    private String cep;
    @NotBlank
    private String city;
    @NotBlank
    @Size(min = 2, max = 2)
    private String uf;

}
