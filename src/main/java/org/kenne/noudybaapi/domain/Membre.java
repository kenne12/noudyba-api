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
public class Membre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_membre")
    private Long idMembre;

    @Column(length = 12)
    @NotEmpty(message = "Member Code can not be empty or null")
    private String code;

    @Column(length = 100)
    @NotEmpty(message = "Member Name can not be empty or null")
    private String nom;

    private String prenom;
    private String contact;
    private String photo;
    private boolean state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ville", referencedColumnName = "id_ville")
    private Ville ville;
}
