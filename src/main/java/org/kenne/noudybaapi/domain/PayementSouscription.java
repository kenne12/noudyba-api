package org.kenne.noudybaapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "payementsouscription")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PayementSouscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_payementsouscription")
    private Long idPayementSouscription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_souscription", referencedColumnName = "id_souscription" , updatable = false)
    private Souscription souscription;

    @Min(value = 1 , message = "Payment amount can not be less than 1")
    private Double montant;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_payement" , nullable = false)
    private Date datePayement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_operation" , referencedColumnName = "id_operation" , updatable = false)
    private Operation operation;
}
