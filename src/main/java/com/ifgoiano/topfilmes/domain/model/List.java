package com.ifgoiano.topfilmes.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idList;
    @NotNull
    private Date addition;

    // Relacionamentos

    @OneToMany(mappedBy = "list")
    private java.util.List<Movie> movies;

    // Relaciona mas não retorna no json

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
