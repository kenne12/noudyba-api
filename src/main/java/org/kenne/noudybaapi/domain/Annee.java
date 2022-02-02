package org.kenne.noudybaapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Annee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_annee")
    private Integer idAnnee;

    @Column(length = 25, nullable = false)
    @NotEmpty(message = "Name can not be empty or null")
    private String nom;
    @NotEmpty(message = "Code can not be empty or null")
    @Column(length = 10)
    private String code;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_debut")
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_fin")
    private Date dateFin;

    private boolean etat;
    private boolean cloturee;

    public Annee(Integer idAnnee) {
        this.idAnnee = idAnnee;
    }
}
