package com.ifgoiano.topfilmes.domain.model;

import com.ifgoiano.topfilmes.domain.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovie;
    @NotBlank
    private String title;
    @NotBlank
    private String director;
    @Enumerated @NotNull
    private Gender gender;
    @NotBlank @Size(max = 280)
    private String sinopse;
    private Date launch;
    @NotNull
    private int duration;
    @NotNull
    private int classification;
    @NotBlank
    private String distributor;

    // Relacionamentos

    @OneToMany(mappedBy = "movie")
    private java.util.List<Avaliation> avaliations;

    @OneToMany(mappedBy = "movie")
    private java.util.List<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    private java.util.List<Actor> actors;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToMany
    @JoinTable(
            name = "movie_list",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    private java.util.List<List> list;

    @JsonIgnore
    @ManyToMany(mappedBy = "movies")
    private java.util.List<Session> sessions;

}
