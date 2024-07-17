package com.ifgoiano.demo.api.dto.login;

import com.ifgoiano.demo.domain.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

    private Long idUser;
    private String token;
    private Roles role;
}
