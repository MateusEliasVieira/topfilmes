package com.ifgoiano.demo.domain.model;

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
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movies;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "session_cinema",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "cinema_id")
    )
    private List<Cinema> cinemas;

    // Relaciona mas não retorna no json

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "session_user",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

}
