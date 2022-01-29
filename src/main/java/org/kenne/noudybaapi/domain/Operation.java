package org.kenne.noudybaapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kenne.noudybaapi.enumeration.OperationType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_operation")
    private Long idOperation;

    @Min(1)
    private Double montant;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_operation", nullable = false)
    private Date dateOperation;

    @Temporal(TemporalType.TIME)
    private Date heure;

    private String libelle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_annee", referencedColumnName = "id_annee")
    private Annee annee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_membre", referencedColumnName = "id_membre")
    @NotNull
    private Membre membre;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_operation")
    private OperationType operationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rubrique", referencedColumnName = "id_rubrique")
    @NotNull
    private Rubrique rubrique;
}
