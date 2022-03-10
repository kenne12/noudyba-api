package org.kenne.noudybaapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class PeriodePK implements Serializable {

    @Column(name = "id_mois")
    @NotNull
    private Integer idMois;


    @Column(name = "id_annee")
    @NotNull
    private Integer idAnnee;

}
