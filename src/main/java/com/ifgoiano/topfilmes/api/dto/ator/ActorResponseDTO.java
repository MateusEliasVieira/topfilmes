package com.ifgoiano.topfilmes.api.dto.ator;

import com.ifgoiano.topfilmes.api.dto.filme.MovieResponseDTO;
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
public class ActorResponseDTO {
    private Long idActor;
    private String name;
    private Date dateOfBirth;
    private List<MovieResponseDTO> movies;
}
