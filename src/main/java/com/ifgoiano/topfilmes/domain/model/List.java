package com.ifgoiano.topfilmes.domain.model;

import jakarta.persistence.*;
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
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idList;
    private Date addition = new Date();

    // Relacionamentos

    @ManyToMany(mappedBy = "list")
    private java.util.List<Movie> movies = new ArrayList<>();

    // Relaciona mas não retorna no json

    @OneToOne
    @JoinColumn
    private User user;

}
