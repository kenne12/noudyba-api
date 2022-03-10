package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EvenementRequestDTO {
    private Long idEvenement;
    private String code;
    @NotNull(message = "Event Rubric can not be null")
    private Integer idRubrique;
    @NotNull(message = "Event Year can not be null")
    private Integer idAnnee;
    private Date dateDebut;
    private Date dateFin;
    private String commentaire;
}
