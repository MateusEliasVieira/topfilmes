package com.ifgoiano.topfilmes.api.dto.comment;

import com.ifgoiano.topfilmes.api.dto.movie.MovieIDRequestDTO;
import com.ifgoiano.topfilmes.api.dto.user.UserIDRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDTO {

    private Long idComment;
    @NotBlank
    @Size(max = 280)
    private String text;
    @NotNull
    private Date dateHour = new Date();
    @NotNull
    private MovieIDRequestDTO movie; // Informa apenas o id do filme de referência
    @NotNull
    private UserIDRequestDTO user; // Informa apenas o id do usuário de referência
}
