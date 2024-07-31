package com.ifgoiano.topfilmes.domain.model;

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
    @NotBlank(message = PersonalizedResponse.USUARIO_ATR_NOME_VAZIO)
    private String name;
    @Email
    @NotBlank(message = PersonalizedResponse.USUARIO_ATR_EMAIL_VAZIO)
    private String email;
    @NotBlank(message = PersonalizedResponse.USUARIO_ATR_USUARIO_VAZIO)
    @Size(min = 6, max = 20)
    private String user;
    @NotBlank(message = PersonalizedResponse.USUARIO_ATR_SENHA_VAZIO)
    @Size(min = 8)
    private String password;
    @NotNull
    private Date dateRegister= new Date();
    @NotNull(message = PersonalizedResponse.USUARIO_ATR_DATANASCIMENTO_VAZIO)
    private Date dateOfBirth;
    @CPF
    @Column(unique = true)
    private String cpf;
    @Enumerated
    @NotNull
    private Roles role;

    // Relacionamentos

    @OneToOne(mappedBy = "user")
    private List list;

    // Relaciona mas não retorna no json

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private java.util.List<Movie> movies;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private java.util.List<Avaliation> avaliations;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private java.util.List<Comment> comments;

    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    private java.util.List<Session> sessions;

    @JsonIgnore
    @OneToMany(mappedBy = "user") // usuário admin cadastra
    private java.util.List<Cinema> cinemas;

}
