package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SouscriptionRequestDTO {

    private Long idSouscription;
    @NotNull(message = "idEvenement can not be null")
    private Long idEvenement;
    @NotNull(message = "idMembre can not be null")
    private Long idMembre;
    @NotNull(message = "Amount can not be null")
    @Min(value = 1, message = "Souscription amount can not be less than 1")
    private Double montant;
    private Date dateSouscription;
    private String libelle;
}
