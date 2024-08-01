package com.ifgoiano.topfilmes.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Size(max = 280)
    private String text;
    private Date dateHour = new Date();

    // Relacionamentos

    @ManyToOne
    @JoinColumn
    private User user;

    // Relaciona mas não retorna no json

    @ManyToOne
    @JoinColumn
    private Movie movie;

}
