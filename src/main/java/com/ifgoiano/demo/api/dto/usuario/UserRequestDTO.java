package com.ifgoiano.demo.api.dto.usuario;

import com.ifgoiano.demo.api.dto.endereco.AddressRequestDTO;
import com.ifgoiano.demo.domain.enums.Roles;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private Long idUser;
    @NotBlank
    private String name;
    @Email @NotBlank
    private String email;
    @NotBlank @Size(min = 6, max = 20)
    private String user;
    @NotBlank @Size(min = 8)
    private String password;
    @NotNull
    private Date dateRegister = new Date();
    @NotNull
    private Date dateOfBirth;
    @CPF @NotBlank
    private String cpf;
    @Enumerated
    @NotNull
    private Roles role = Roles.ROLE_USER;
    private AddressRequestDTO address;

}
