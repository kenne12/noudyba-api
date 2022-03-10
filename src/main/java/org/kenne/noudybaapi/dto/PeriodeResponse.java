package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kenne.noudybaapi.domain.Mois;
import org.kenne.noudybaapi.domain.PeriodePK;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeriodeResponse {

    private PeriodePK periodePK;
    private String shortName;
    private Integer numero;
    private Date dateDebut;
    private Date dateFin;
    private Mois mois;
    private AnneeResponseDTO annee;
}
