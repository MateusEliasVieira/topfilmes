package com.ifgoiano.demo.api.dto.usuario;

import com.ifgoiano.demo.api.dto.endereco.AddressResponseDTO;
import com.ifgoiano.demo.api.dto.lista.ListResponseDTO;
import com.ifgoiano.demo.domain.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long idUser;
    private String name;
    private String email;
    private String user;
    private Date dateRegister = new Date();
    private Date dateOfBirth;
    private Roles role = Roles.ROLE_USER;

    // Relacionamentos
    private AddressResponseDTO endereco;
    private ListResponseDTO lista;

}
