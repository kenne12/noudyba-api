package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kenne.noudybaapi.domain.Souscription;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PayementSouscriptionResponseDTO {

    private Long idPayementSouscription;
    private Souscription souscription;
    private Double montant;
    private Date datePayement;
}
