package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kenne.noudybaapi.enumeration.OperationType;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OperationResponseDTO {
    private Long idOperation;
    private Double montant;
    private Date dateOperation;
    private Date heure;
    private String libelle;
    private AnneeResponseDTO annee;
    private MembreResponseDTO membre;
    private OperationType operationType;
    private RubriqueResponseDTO rubrique;
}
