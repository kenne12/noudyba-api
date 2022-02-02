package org.kenne.noudybaapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contribution")
    private Long idContribution;

    @Min(value = 1 , message = "Amount can not be less than 1")
    private double montant;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_contribution")
    private Date dateContribution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evenement", referencedColumnName = "id_evenement")
    private Evenement evenement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_membre", referencedColumnName = "id_membre", updatable = false)
    private Membre membre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_operation", referencedColumnName = "id_operation", updatable = false)
    private Operation operation;
}
