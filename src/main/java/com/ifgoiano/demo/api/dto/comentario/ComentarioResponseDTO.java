package com.ifgoiano.demo.api.dto.comentario;

import com.ifgoiano.demo.api.dto.usuario.UsuarioResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioResponseDTO {

    private Long idComentario;
    private String texto;
    private Date dataHora;

    // Relacionamento
    private UsuarioResponseDTO usuario;

}
