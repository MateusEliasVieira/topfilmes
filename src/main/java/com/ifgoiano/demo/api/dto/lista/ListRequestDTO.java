package com.ifgoiano.demo.api.dto.lista;

import com.ifgoiano.demo.api.dto.filme.MovieRequestDTO;
import com.ifgoiano.demo.api.dto.usuario.UserRequestDTO;
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
