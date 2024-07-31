package com.ifgoiano.topfilmes.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCinema;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String operation;
    @NotNull
    private int rooms;
    @CNPJ
    private String cnpj;

    // Relacionamentos

    @ManyToMany(mappedBy = "cinemas")
    private List<Session> sessions;

    // Relaciona mas não retorna no json

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private User user;

}
