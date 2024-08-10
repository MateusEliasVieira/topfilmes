package com.ifgoiano.topfilmes.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.ArrayList;
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
    private String name;
    private String address;
    private String operation;
    private int rooms;
    @CNPJ
    private String cnpj;


    @ManyToMany(mappedBy = "cinemas", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Session> sessions = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private User user;

}
