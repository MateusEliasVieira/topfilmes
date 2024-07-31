package com.ifgoiano.topfilmes.api.dto.actor;

import com.ifgoiano.topfilmes.api.dto.movie.MovieResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorIDRequestDTO {
    private Long idActor;

}
