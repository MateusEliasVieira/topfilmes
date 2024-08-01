package com.ifgoiano.topfilmes.api.dto.list;

import com.ifgoiano.topfilmes.api.dto.movie.MovieResponseDTO;
import com.ifgoiano.topfilmes.domain.model.Movie;
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
public class ListResponseDTO {

    private Long idList;
    private Date addition;

    // Relacionamentos
    private List<MovieResponseDTO> movies;
}
