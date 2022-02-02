package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContributionRequestDTO {

    private Long idContribution;
    @Min(value = 1, message = "Amount can be less than 1")
    private double montant;
    private Date dateContribution;
    private Long idEvenement;
    private Long idMembre;
}
