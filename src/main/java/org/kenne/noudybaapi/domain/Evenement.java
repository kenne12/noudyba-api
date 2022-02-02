package org.kenne.noudybaapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_evenement")
    private Long idEvenement;

    @NotNull(message = "Event code not be null")
    @Length(min = 8, max = 10, message = "The length of event code is not correct")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rubrique", referencedColumnName = "id_rubrique")
    @NotNull
    private Rubrique rubrique;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_annee", referencedColumnName = "id_annee")
    @NotNull
    private Annee annee;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_debut")
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_fin")
    private Date dateFin;

    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @Column(length = 100)
    private String commentaire;

    public Evenement(Long idEvenement) {
        this.idEvenement = idEvenement;
    }
}
