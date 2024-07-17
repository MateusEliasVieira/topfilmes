package com.ifgoiano.demo.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Actor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtor;
    @NotBlank
    private String name;
    private Date dateOfBirth;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;

}
