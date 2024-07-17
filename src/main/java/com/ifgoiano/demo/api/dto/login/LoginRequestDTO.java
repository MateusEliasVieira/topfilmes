package com.ifgoiano.demo.api.dto.login;

import com.ifgoiano.demo.utils.PersonalizedResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = PersonalizedResponse.LOGIN_ATR_USUARIO_VAZIO)
    @Size(min = 6, max = 20, message = PersonalizedResponse.LOGIN_ATR_USUARIO_TAMANHO)
    private String usuario;
    @NotBlank(message = PersonalizedResponse.LOGIN_ATR_SENHA_VAZIO)
    @Size(min = 8, message = PersonalizedResponse.LOGIN_ATR_SENHA_TAMANHO)
    private String senha;
}
