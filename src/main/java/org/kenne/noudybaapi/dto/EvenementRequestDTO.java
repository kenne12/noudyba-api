package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EvenementRequestDTO {
    private Long idEvenement;
    @NotNull(message = "Event code not be null")
    @Length(min = 8 , max = 10 , message = "The length of event code is not correct")
    private String code;
    private Integer idRubrique;
    private Integer idAnnee;
    private Date dateDebut;
    private Date dateFin;
    private String commentaire;
}
