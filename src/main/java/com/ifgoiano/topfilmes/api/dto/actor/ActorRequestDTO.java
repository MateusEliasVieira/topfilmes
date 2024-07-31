package com.ifgoiano.topfilmes.api.dto.actor;

import com.ifgoiano.topfilmes.api.dto.movie.MovieIDRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorRequestDTO {

    private Long idActor;
    private String name;
    private Date dateOfBirth;
    private MovieIDRequestDTO movie; // Informa apenas o id do filme de referência

}
