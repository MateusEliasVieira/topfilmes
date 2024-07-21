package com.ifgoiano.topfilmes.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAddress;
    @NotBlank
    private String street;
    private String number;
    @NotBlank
    private String neighborhood;
    @NotNull
    @Size(min = 9, max = 9)
    private String cep;
    @NotBlank
    private String city;
    @NotBlank
    @Size(min = 2, max = 2)
    private String uf;

    // Relaciona mas não retorna no json

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
