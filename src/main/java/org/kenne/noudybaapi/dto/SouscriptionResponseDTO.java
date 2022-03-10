package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SouscriptionResponseDTO {

    private Long idSouscription;
    private EvenementResponseDTO evenement;
    private MembreResponseDTO membre;
    private Double montant;
    private Double montantPaye;
    private Date dateSouscription;
    private String libelle;
}
