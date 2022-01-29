package org.kenne.noudybaapi.dto;

import lombok.Builder;
import org.kenne.noudybaapi.domain.Evenement;

import java.util.Date;

@Builder
public class ContributionResponseDTO {

    private Long idContribution;
    private double montant;
    private Date dateContribution;
    private Evenement evenement;
}
