package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kenne.noudybaapi.domain.Annee;
import org.kenne.noudybaapi.domain.Rubrique;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EvenementResponseDTO {
    private Long idEvenement;
    private String code;
    private Rubrique rubrique;
    private Annee annee;
    private Date dateDebut;
    private Date dateFin;
    private Date dateCreation;
    private String commentaire;
}
