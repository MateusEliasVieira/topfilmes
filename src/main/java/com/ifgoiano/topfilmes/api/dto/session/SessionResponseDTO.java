package com.ifgoiano.topfilmes.api.dto.session;

import com.ifgoiano.topfilmes.api.dto.cinema.CinemaResponseDTO;
import com.ifgoiano.topfilmes.api.dto.movie.MovieResponseDTO;
import com.ifgoiano.topfilmes.api.dto.user.UserResponseDTO;
import com.ifgoiano.topfilmes.domain.model.Movie;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponseDTO {

    private Long idSession;
    private Date time;
    private int tickets;
    private int room;
    private int codSession;

    // Relacionamentos
    private UserResponseDTO user;
    private List<MovieResponseDTO> movies;
    private List<CinemaResponseDTO> cinemas;

}
