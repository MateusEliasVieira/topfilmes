package com.ifgoiano.topfilmes.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Actor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActor;
    private String name;
    private Date dateOfBirth;

    @ManyToMany(mappedBy = "actors", cascade = CascadeType.PERSIST)
    @JsonIgnore // Evitar ciclos de serialização JSON
    private List<Movie> movies = new ArrayList<>();


}
