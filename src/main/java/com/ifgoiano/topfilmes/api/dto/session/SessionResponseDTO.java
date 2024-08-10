package com.ifgoiano.topfilmes.api.dto.session;

import com.ifgoiano.topfilmes.api.dto.cinema.CinemaSessionResponseDTO;
import com.ifgoiano.topfilmes.api.dto.movie.MovieResponseDTO;
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
    private Date start;
    private Date end;
    private int tickets;
    private int room;
    private Long codSession;

    private List<CinemaSessionResponseDTO> cinemas;
    private List<MovieResponseDTO> movies;

}
