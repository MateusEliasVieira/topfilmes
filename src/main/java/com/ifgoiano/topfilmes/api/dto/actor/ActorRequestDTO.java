package com.ifgoiano.topfilmes.api.dto.actor;

import com.ifgoiano.topfilmes.api.dto.movie.MovieIDRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    private String name;
    @NotNull
    private Date dateOfBirth;

}
