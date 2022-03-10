package org.kenne.noudybaapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Periode {

    @EmbeddedId
    private PeriodePK periodePK;

    private String shortName;
    private Integer numero;
    private Date dateDebut;
    private Date dateFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mois", referencedColumnName = "id" , insertable = false , updatable = false)
    private Mois mois;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_annee", referencedColumnName = "id_annee" , insertable = false , updatable = false)
    private Annee annee;

    public Periode(PeriodePK periodePK) {
        this.periodePK = periodePK;
    }

    public Periode(int idAnnee, int idMois) {
        this.periodePK = new PeriodePK(idAnnee, idMois);
    }
}
