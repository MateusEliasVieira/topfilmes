package com.ifgoiano.topfilmes.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ifgoiano.topfilmes.domain.enums.Roles;
import com.ifgoiano.topfilmes.utils.PersonalizedResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String name;
    @Column(unique = true)
    private String email;
    @Size(min = 6, max = 20)
    @Column(unique = true)
    private String user;
    @Size(min = 8)
    private String password;
    private Date dateRegister = new Date();
    private Date dateOfBirth;
    @Column(unique = true)
    private String cpf;
    @Enumerated
    private Roles role;

    // Relacionamentos
    @OneToOne(mappedBy = "user")
    private List list;

    // Relaciona mas não retorna no json
    @OneToMany(mappedBy = "user")
    private java.util.List<Movie> movies;

    @OneToMany(mappedBy = "user")
    private java.util.List<Avaliation> avaliations;

    @OneToMany(mappedBy = "user")
    private java.util.List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private java.util.List<Session> sessions;

    @OneToMany(mappedBy = "user") // usuário admin cadastra
    private java.util.List<Cinema> cinemas;

}
