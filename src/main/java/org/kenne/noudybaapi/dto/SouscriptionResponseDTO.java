package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kenne.noudybaapi.domain.Evenement;
import org.kenne.noudybaapi.domain.Membre;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SouscriptionResponseDTO {

    private Long idSouscription;
    private Evenement evenement;
    private Membre membre;
    private Double montant;
    private Double montantPaye;
    private Date dateSouscription;
}
