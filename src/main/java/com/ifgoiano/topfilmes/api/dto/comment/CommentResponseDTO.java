package com.ifgoiano.topfilmes.api.dto.comment;

import com.ifgoiano.topfilmes.api.dto.movie.MovieResponseDTO;
import com.ifgoiano.topfilmes.api.dto.user.UserResponseDTO;
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
    private MovieResponseDTO movie;

}
