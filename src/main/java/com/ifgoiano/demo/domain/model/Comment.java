package com.ifgoiano.demo.domain.model;

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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComments;
    @NotBlank
    @Size(max = 280)
    private String text;
    @NotNull
    private Date dateHour;

    // Relacionamentos

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Relaciona mas não retorna no json

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

}
