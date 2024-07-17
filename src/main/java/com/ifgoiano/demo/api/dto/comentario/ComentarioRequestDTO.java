package com.ifgoiano.demo.api.dto.comentario;

import com.ifgoiano.demo.domain.model.Movie;
import com.ifgoiano.demo.domain.model.User;
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
public class ComentarioRequestDTO {

    @NotBlank
    @Size(max = 280)
    private String texto;
    @NotNull
    private Date dataHora = new Date();
    @NotNull
    private Movie movie;
    @NotNull
    private User user;
}
