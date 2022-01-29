package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kenne.noudybaapi.domain.*;
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
    private Annee annee;
    private Membre membre;
    private OperationType operationType;
    private Rubrique rubrique;
}
