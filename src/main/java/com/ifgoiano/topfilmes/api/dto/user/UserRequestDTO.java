package com.ifgoiano.topfilmes.api.dto.user;

import com.ifgoiano.topfilmes.domain.enums.Roles;
import com.ifgoiano.topfilmes.utils.PersonalizedResponse;
import jakarta.persistence.Column;
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
    @NotBlank(message = PersonalizedResponse.USUARIO_ATR_NOME_VAZIO)
    private String name;
    @Email
    @NotBlank(message = PersonalizedResponse.USUARIO_ATR_EMAIL_VAZIO)
    private String email;
    @NotBlank(message = PersonalizedResponse.USUARIO_ATR_USUARIO_VAZIO)
    @Size(min = 6, max = 20)
    private String user;
    @NotBlank(message = PersonalizedResponse.USUARIO_ATR_SENHA_VAZIO)
    @Size(min = 8)
    private String password;
    @NotNull(message = PersonalizedResponse.USUARIO_ATR_DATANASCIMENTO_VAZIO)
    private Date dateOfBirth;
    @CPF
    @Column(unique = true)
    private String cpf;
    @Enumerated
    @NotNull
    private Roles role;


}
