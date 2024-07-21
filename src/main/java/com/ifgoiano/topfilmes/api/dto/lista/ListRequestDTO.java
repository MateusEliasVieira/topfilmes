package com.ifgoiano.topfilmes.api.dto.lista;

import com.ifgoiano.topfilmes.api.dto.filme.MovieRequestDTO;
import com.ifgoiano.topfilmes.api.dto.usuario.UserRequestDTO;
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
public class ListRequestDTO {

    private Date addition = new Date();

    // Relacionamentos
    private UserRequestDTO user;
    private List<MovieRequestDTO> movies;
}
