package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kenne.noudybaapi.enumeration.OperationType;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OperationRequestDTO {
    private Long idOperation;
    private Double montant;
    private Date dateOperation;
    private Date heure;
    private String libelle;
    private Integer idAnnee;
    private Long idMembre;
    private OperationType operationType;
    private Integer idRubrique;
}
