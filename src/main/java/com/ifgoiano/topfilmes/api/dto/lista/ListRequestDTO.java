package com.ifgoiano.topfilmes.api.dto.lista;

import com.ifgoiano.topfilmes.api.dto.movie.MovieIDRequestDTO;
import com.ifgoiano.topfilmes.api.dto.user.UserRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListRequestDTO {

    private Long idList;
    private Date addition = new Date();

    // Relacionamentos
    private UserRequestDTO user; // Informa apenas o id do usuário de referência
    private MovieIDRequestDTO movie; // Informa apenas o id do filme de referência
}
