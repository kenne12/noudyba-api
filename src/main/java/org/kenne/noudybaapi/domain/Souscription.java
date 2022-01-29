package org.kenne.noudybaapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Souscription {

    @Id
    @Column(name = "id_souscription")
    private Long idSouscription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evenement", referencedColumnName = "id_evenement", nullable = false)
    private Evenement evenement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_membre", referencedColumnName = "id_membre", nullable = false)
    private Membre membre;

    @Column(name = "montant_souscrit")
    @NotNull(message = "Amount can not be null")
    @Min(value = 1, message = "The value of amount can not be less than 1")
    private Double montant;

    @Column(name = "montant_paye")
    private Double montantPaye;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_souscription")
    private Date dateSouscription;
}
