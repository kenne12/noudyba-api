package org.kenne.noudybaapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bureau implements Serializable {

    @EmbeddedId
    private BureauPK bureauPK;

    public Bureau(Long idMembre, Integer idPoste, Integer idAnnee) {
        this.bureauPK = new BureauPK(idMembre, idPoste, idPoste);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_membre", referencedColumnName = "id_membre" , insertable = false , updatable = false)
    private Membre membre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_poste", referencedColumnName = "id_poste" , insertable = false , updatable = false)
    private Poste poste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_annee", referencedColumnName = "id_annee" , insertable = false , updatable = false)
    private Annee annee;
}
