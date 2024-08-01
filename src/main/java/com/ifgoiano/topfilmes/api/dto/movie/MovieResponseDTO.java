package com.ifgoiano.topfilmes.api.dto.movie;

import com.ifgoiano.topfilmes.api.dto.actor.ActorResponseDTO;
import com.ifgoiano.topfilmes.api.dto.avaliation.AvaliationResponseDTO;
import com.ifgoiano.topfilmes.api.dto.comment.CommentResponseDTO;
import com.ifgoiano.topfilmes.api.dto.list.ListResponseDTO;
import com.ifgoiano.topfilmes.api.dto.session.SessionResponseDTO;
import com.ifgoiano.topfilmes.api.dto.user.UserResponseDTO;
import com.ifgoiano.topfilmes.domain.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDTO {

    private Long idMovie;
    private String title;
    private String director;
    private Gender gender;
    private String sinopse;
    private Date launch;
    private int duration;
    private int classification;
    private String distributor;

    private List<ActorResponseDTO> actors;
    private List<CommentResponseDTO> comments;
    private List<AvaliationResponseDTO> avaliations;

}
