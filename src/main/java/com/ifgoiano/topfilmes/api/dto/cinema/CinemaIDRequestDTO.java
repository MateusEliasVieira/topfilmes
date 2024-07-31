package com.ifgoiano.topfilmes.api.dto.cinema;

import com.ifgoiano.topfilmes.api.dto.session.SessionResponseDTO;
import com.ifgoiano.topfilmes.api.dto.user.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CinemaIDRequestDTO {

    private Long idCinema;

}
