package com.ifgoiano.topfilmes.api.dto.comentario;

import com.ifgoiano.topfilmes.api.dto.usuario.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {

    private Long idComment;
    private String text;
    private Date dateHour;

    // Relacionamento
    private UserResponseDTO user;

}
