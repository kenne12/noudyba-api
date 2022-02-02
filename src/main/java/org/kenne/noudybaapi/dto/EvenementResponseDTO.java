package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EvenementResponseDTO {
    private Long idEvenement;
    private String code;
    private RubriqueResponseDTO rubrique;
    private AnneeResponseDTO annee;
    private Date dateDebut;
    private Date dateFin;
    private Date dateCreation;
    private String commentaire;
}
