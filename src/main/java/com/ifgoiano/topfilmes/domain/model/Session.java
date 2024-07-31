package com.ifgoiano.topfilmes.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSession;
    @NotNull
    private Date time;
    @NotNull
    private int tickets;
    @NotNull
    private int room;
    @NotNull
    private int codSession;

    // Relacionamentos

    @ManyToMany
    @JoinTable(
            name = "session_movie",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    private List<Movie> movies;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "session_cinema",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    private List<Cinema> cinemas;

    // Relaciona mas não retorna no json

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "session_user",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    private List<User> users;

}
