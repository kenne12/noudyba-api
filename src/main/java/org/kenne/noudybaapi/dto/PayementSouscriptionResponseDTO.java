package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PayementSouscriptionResponseDTO {

    private Long idPayementSouscription;
    private SouscriptionResponseDTO souscription;
    private Double montant;
    private Date datePayement;
}
