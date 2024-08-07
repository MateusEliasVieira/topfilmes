package com.ifgoiano.topfilmes.domain.model;

import com.ifgoiano.topfilmes.domain.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovie;
    @Column(unique = true)
    private String title;
    private String director;
    @Enumerated
    private Gender gender;
    @Size(max = 280)
    private String sinopse;
    private Date launch;
    private double duration;
    private int classification;
    private String distributor;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] cover;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] trailer;

    // Relacionamentos

    @OneToMany(mappedBy = "movie")
    private java.util.List<Avaliation> avaliations = new ArrayList<>();
 
    @OneToMany(mappedBy = "movie")
    private java.util.List<Comment> comments = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    private java.util.List<Actor> actors = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn
    private User user;
 
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "movie_list",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    private java.util.List<List> list;

    @ManyToMany(mappedBy = "movies")
    private java.util.List<Session> sessions = new ArrayList<>();

}
