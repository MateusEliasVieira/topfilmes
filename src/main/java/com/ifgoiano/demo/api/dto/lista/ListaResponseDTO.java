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
public class ListaResponseDTO {

    private Long idLista;
    private Date adicao;

    // Relacionamentos
    private List<Movie> movies;
}
