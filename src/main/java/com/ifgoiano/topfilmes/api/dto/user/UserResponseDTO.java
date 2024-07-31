package com.ifgoiano.topfilmes.api.dto.user;

import com.ifgoiano.topfilmes.api.dto.actor.ActorResponseDTO;
import com.ifgoiano.topfilmes.api.dto.avaliation.AvaliationResponseDTO;
import com.ifgoiano.topfilmes.api.dto.cinema.CinemaResponseDTO;
import com.ifgoiano.topfilmes.api.dto.comment.CommentResponseDTO;
import com.ifgoiano.topfilmes.api.dto.lista.ListResponseDTO;
import com.ifgoiano.topfilmes.api.dto.movie.MovieResponseDTO;
import com.ifgoiano.topfilmes.api.dto.session.SessionResponseDTO;
import com.ifgoiano.topfilmes.domain.enums.Roles;
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
public class UserResponseDTO {

    private Long idUser;
    private String name;
    private String email;
    private String user;
    private Date dateRegister;
    private Date dateOfBirth;
    private Roles role = Roles.ROLE_USER;

    // Relacionamentos
    private ListResponseDTO list;
    private List<ActorResponseDTO> actors;
    private List<MovieResponseDTO> movies;
    private List<SessionResponseDTO> sessions;
    private List<CinemaResponseDTO> cinemas;
    private List<CommentResponseDTO> comments;
    private List<AvaliationResponseDTO> avaliations;

}
