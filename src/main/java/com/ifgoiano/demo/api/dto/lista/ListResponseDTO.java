package com.ifgoiano.demo.api.dto.lista;

import com.ifgoiano.demo.domain.model.Movie;
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
    private List<Movie> movies;
}
