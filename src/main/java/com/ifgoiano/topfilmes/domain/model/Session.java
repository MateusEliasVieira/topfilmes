package com.ifgoiano.topfilmes.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSession;
    private Date start;
    private Date end;
    private int tickets;
    private int room;
    @Column(unique = true)
    private Long codSession;

    // Relacionamentos

    @ManyToMany
    @JoinTable(
            name = "session_movie",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    private List<Movie> movies = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "session_cinema",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    private List<Cinema> cinemas = new ArrayList<>();


    @ManyToOne
    @JoinColumn
    private User user;

}
