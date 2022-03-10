package org.kenne.noudybaapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class ContributionResponseDTO {

    private Long idContribution;
    private double montant;
    private Date dateContribution;
    private EvenementResponseDTO evenement;
    private MembreResponseDTO membre;
    private String libelle;
}
