package org.kenne.noudybaapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Poste {
    @Id
    @Column(name = "id_poste")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPoste;

    @Column(length = 10)
    private String code;

    @NotEmpty(message = "Name can not be empty or null")
    @Column(length = 30)
    private String nom;

    public Poste(Integer idPoste) {
        this.idPoste = idPoste;
    }
}
